package com.bgp.mgr.datagrab;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bgp.mgr.service.ElasticsearchService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrabFromBgg {

    // 目标网址
    private static final String SOURCE_URL = "https://bgg-json.azurewebsites.net/thing/{gameId}";

    // 临时目录
    private static final String TEMP_PATH = "myPath/";

    // CSV文件头
    private static final String[] FILE_HEADER = {"gameId", "responseCode", "responseContent"};

    // 时间戳
    private String datetimeStamp;

    // 最大重试次数
    private int maxRetryTimes;

    // 结果集
    private List<DataVo> dataVoList = new ArrayList<>();

    // ES索引
    private String indexName;

    @Resource
    private ElasticsearchService elasticsearchService;

    @Before
    public void init() {
        datetimeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        maxRetryTimes = 5;
        indexName = "bgg_game_info";
    }

    /**
     * 获取一条数据
     */
    @Test
    public void grabData() {
        /* game id 取值范围 1-318398 */
        int id = 100;
        DataVo result = this.grab(id);
        System.out.println(result.getGameId() + " " + result.getResponseCode() + " " + result.getResponseContent());
    }

    /**
     * 循环获取指定区间数据
     */
    @Test
    public void grabDataRange() {
        /* game id 取值范围 1-318398 */
        int gameIdStart = 5001;
        int gameIdEnd = 10000;

        for (int id = gameIdStart; id < gameIdEnd + 1; id++) {
            dataVoList.add(new DataVo(id, -1, null));
        }
        // 递归调用
        int failCount = this.grabRecursion(maxRetryTimes);
        // 打印失败率
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        float fRate = (float) failCount / (float) dataVoList.size() * 100;
        System.out.println("Failure Rate: " + numberFormat.format(fRate) + "%");
        // 导出CSV
        String fileName = "bgg_" + gameIdStart + "_" + gameIdEnd + "_" + datetimeStamp + ".csv";
        this.exportCSV(fileName);
    }

    /**
     * 循环获取指定区间数据（ES）
     */
    @Test
    public void grabDataRangeToES() {
        /* game id 取值范围 1-318398 */
        int gameIdStart = 15001;
        int gameIdEnd = 20000;

        int failCount = 0;
        for (int id = gameIdStart; id < gameIdEnd + 1; id++) {
            if (!elasticsearchService.exists(indexName, String.valueOf(id))) {
                DataVo dataVo = this.grab(id);
                if (dataVo.getResponseCode() == 200) {
                    elasticsearchService.add(indexName, dataVo.getResponseContent(), String.valueOf(dataVo.getGameId()));
                } else {
                    failCount++;
                }
            }
        }
        // 打印失败率
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        float fRate = (float) failCount / (float) (gameIdEnd - gameIdStart) * 100;
        System.out.println("Failure Rate: " + numberFormat.format(fRate) + "%");
    }

    /**
     * 根据CSV文件结果进行失败重试
     */
    @Test
    public void grabDataRetry() {
        // 待重试文件名
        String fileName = "bgg_5001_10000_20200911180717.csv";

        this.importCSV(fileName);
        // 递归调用
        int failCount = this.grabRecursion(maxRetryTimes);
        // 打印失败率
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        float fRate = (float) failCount / (float) dataVoList.size() * 100;
        System.out.println("Failure Rate: " + numberFormat.format(fRate) + "%");
        // 导出CSV
        fileName = fileName.substring(0, fileName.length() - 18) + datetimeStamp + ".csv";
        this.exportCSV(fileName);
    }

    /**
     * 合并CSV
     */
    @Test
    public void mergeCSV() {
        this.importCSV("bgg_1_1000_20200911140635.csv");
        this.importCSV("bgg_1001_2000_20200910225740.csv");
        this.importCSV("bgg_2001_3000_20200911110314.csv");
        this.importCSV("bgg_3001_5000_20200911152227.csv");
        this.importCSV("bgg_5001_10000_20200914092800.csv");
        // 目标文件
        this.exportCSV("bgg_1_10000.csv");
    }

    /**
     * CSV转存储ES
     */
    @Test
    public void transferCSVtoES() {
        this.importCSV("bgg_1_10000.csv");
        for (DataVo dataVo : dataVoList) {
            if (dataVo.getResponseCode() == 200) {
                elasticsearchService.add(indexName, dataVo.getResponseContent(), String.valueOf(dataVo.getGameId()));
            }
        }
    }

    /**
     * 查询ES
     */
    @Test
    public void queryFromES() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().from(0).size(100).sort("_id", SortOrder.ASC);
        List<JSONObject> result = elasticsearchService.search(indexName, searchSourceBuilder, JSONObject.class);
        System.out.println(result.size());
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 访问URL并获取数据
     *
     * @param id
     * @return
     */
    private DataVo grab(int id) {
        long startTime = System.currentTimeMillis();
        String gameId = String.valueOf(id);
        String url = SOURCE_URL.replace("{gameId}", gameId);

        Response httpResponse = Http.get(url);
        DataVo data = new DataVo(id,
                httpResponse != null ? httpResponse.getStatus() : -1,
                httpResponse != null ? httpResponse.getContent() : "");
        System.out.println("GET " + url + " " + data.getResponseCode() + " "
                + (System.currentTimeMillis() - startTime) + "ms");
        return data;
    }

    /**
     * 获取数据并递归重试
     *
     * @param maxRetryTimes
     */
    private int grabRecursion(int maxRetryTimes) {
        if (CollectionUtils.isEmpty(dataVoList)) {
            return 0;
        }
        int failCount = 0;
        for (int i = 0; i < dataVoList.size(); i++) {
            DataVo dataVo = dataVoList.get(i);
            if (dataVo.getResponseCode() != 200) {
                DataVo result = this.grab(dataVo.getGameId());
                dataVoList.set(i, result);
                if (result.getResponseCode() != 200) {
                    failCount++;
                }
            }
        }
        if (failCount > 0 && maxRetryTimes > 0) {
            return this.grabRecursion(--maxRetryTimes);
        } else {
            return failCount;
        }
    }

    /**
     * 导出CSV文件
     *
     * @param fileName
     */
    private void exportCSV(String fileName) {
        if (CollectionUtils.isEmpty(dataVoList)) {
            return;
        }
        File file = new File(TEMP_PATH);
        if (!file.exists() && !file.mkdirs()) {
            System.out.println("mkdir " + TEMP_PATH + " failed!");
            return;
        }
        String filePath = TEMP_PATH + fileName;
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        try {
            fileWriter = new FileWriter(filePath);
            csvPrinter = new CSVPrinter(fileWriter, csvFormat);
            for (DataVo result : dataVoList) {
                csvPrinter.printRecord(result.getGameId(), result.getResponseCode(), result.getResponseContent());
            }
            csvPrinter.flush();
            System.out.println("Write to CSV: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvPrinter != null) {
                    csvPrinter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导入CSV内容
     *
     * @param fileName
     */
    private void importCSV(String fileName) {
        String filePath = TEMP_PATH + fileName;
        FileReader fileReader = null;
        CSVParser csvParser = null;
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        try {
            fileReader = new FileReader(filePath);
            csvParser = new CSVParser(fileReader, csvFormat);
            List<CSVRecord> csvRecords = csvParser.getRecords();
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                dataVoList.add(new DataVo(Integer.parseInt(record.get(0)),
                        Integer.parseInt(record.get(1)), record.get(2)));
            }
            System.out.println("Read from CSV: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvParser != null) {
                    csvParser.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static class DataVo {
        private int gameId;
        private int responseCode;
        private String responseContent;

        DataVo(int gameId, int responseCode, String responseContent) {
            this.gameId = gameId;
            this.responseCode = responseCode;
            this.responseContent = responseContent;
        }

        int getGameId() {
            return gameId;
        }

        void setGameId(int gameId) {
            this.gameId = gameId;
        }

        int getResponseCode() {
            return responseCode;
        }

        void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        String getResponseContent() {
            return responseContent;
        }

        void setResponseContent(String responseContent) {
            this.responseContent = responseContent;
        }
    }

}
