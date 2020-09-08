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

    // CSV文件名
    private String fileName;

    private String datetimeStamp;

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

    private DataVo grabOne(int id) {
        long startTime = System.currentTimeMillis();
        String gameId = String.valueOf(id);
        String url = SOURCE_URL.replace("{gameId}", gameId);

        Response httpResponse = Http.get(url);
        if (httpResponse == null || !httpResponse.isOK()) {
            httpResponse = Http.get(url);// 失败再试一次
        }
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
        DataVo result = this.grabOne(id);
        System.out.println(result.getGameId() + " " + result.getResponseCode() + " " + result.getResponseContent());
    }

    @Test
    public void grabDataLoop() {
        /* game id 取值范围 1-318398 */
        int gameIdStart = 1;
        int gameIdEnd = 100;
        fileName = "bgg_" + gameIdStart + "_" + gameIdEnd + "_" + datetimeStamp + ".csv";

        String filePath = TEMP_PATh + fileName;
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        try {
            fileWriter = new FileWriter(filePath);
            csvPrinter = new CSVPrinter(fileWriter, csvFormat);
            for (int id = gameIdStart; id < gameIdEnd + 1; id++) {
                DataVo result = this.grabOne(id);
                csvPrinter.printRecord(result.getGameId(), result.getResponseCode(), result.getResponseContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvPrinter != null) {
                    csvPrinter.flush();
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


    @Test
    public void grabDataRetry() {
        fileName = "bgg_1_100_20200908164153.csv";

        String filePath = TEMP_PATh + fileName;
        FileReader fileReader = null;
        CSVParser csvParser = null;
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        List<DataVo> resultList = new ArrayList<>();
        try {
            fileReader = new FileReader(filePath);
            csvParser = new CSVParser(fileReader, csvFormat);
            List<CSVRecord> csvRecords = csvParser.getRecords();
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                int gameId = Integer.parseInt(record.get(0));
                int responseCode = Integer.parseInt(record.get(1));
                if (responseCode == 200) {
                    resultList.add(new DataVo(gameId, responseCode, record.get(2)));
                } else {
                    resultList.add(this.grabOne(gameId));
                }
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

        fileName = fileName.substring(0, fileName.length() - 18) + datetimeStamp + ".csv";
        filePath = TEMP_PATh + fileName;
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        try {
            fileWriter = new FileWriter(filePath, true);
            csvPrinter = new CSVPrinter(fileWriter, csvFormat);
            for (DataVo result : resultList) {
                csvPrinter.printRecord(result.getGameId(), result.getResponseCode(), result.getResponseContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvPrinter != null) {
                    csvPrinter.flush();
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
}
