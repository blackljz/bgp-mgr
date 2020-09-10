package com.bgp.mgr.datagrab;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.nutz.http.Http;
import org.nutz.http.Response;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(JUnit4.class)
public class GrabFromBgg {

    // 目标网址
    private static final String SOURCE_URL = "https://bgg-json.azurewebsites.net/thing/{gameId}";

    // 临时目录
    private static final String TEMP_PATh = "myPath/";

    // CSV文件头
    private static final String[] FILE_HEADER = {"gameId", "responseCode", "responseContent"};

    // 时间戳
    private String datetimeStamp;

    // 结果集
    private List<DataVo> dataVoList = new ArrayList<>();

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

    @Before
    public void init() {
        datetimeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
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

    @Test
    public void grabData() {
        /* game id 取值范围 1-318398 */
        int id = 100;
        DataVo result = this.grab(id);
        System.out.println(result.getGameId() + " " + result.getResponseCode() + " " + result.getResponseContent());
    }

    @Test
    public void grabDataRange() {
        /* game id 取值范围 1-318398 */
        int gameIdStart = 2001;
        int gameIdEnd = 3000;
        // 最大重试次数
        int maxRetryTimes = 1;

        for (int id = gameIdStart; id < gameIdEnd + 1; id++) {
            dataVoList.add(new DataVo(id, -1, ""));
        }
        // 递归调用
        this.grabRecursion(maxRetryTimes);
        // 导出CSV
        String fileName = "bgg_" + gameIdStart + "_" + gameIdEnd + "_" + datetimeStamp + ".csv";
        this.exportCSV(fileName);
    }

    /**
     * 获取数据并递归重试
     *
     * @param maxRetryTimes
     */
    private void grabRecursion(int maxRetryTimes) {
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
        if (failCount != 0 && maxRetryTimes > 0) {
            this.grabRecursion(--maxRetryTimes);
        }
    }

    /**
     * 导出CSV文件
     *
     * @param fileName
     */
    private void exportCSV(String fileName) {
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
            for (CSVRecord record : csvRecords) {
                dataVoList.add(new DataVo(Integer.parseInt(record.get(0)),
                        Integer.parseInt(record.get(1)), record.get(2)));
            }
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
}
