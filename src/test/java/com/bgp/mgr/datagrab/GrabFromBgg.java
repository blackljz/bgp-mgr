package com.bgp.mgr.datagrab;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrabFromBgg {

    private static final String SOURCE_URL = "https://bgg-json.azurewebsites.net/thing/{gameId}";

    private static final String TEMP_PATh = "myPath/";

    /**
     * game id 取值范围 1-318398
     */
    private int id, gameIdStart, gameIdEnd;
    private String fileName;

    @Before
    public void init() {
        id = 1;

        gameIdStart = 1;
        gameIdEnd = 100;
        fileName = "bgg_game_info_" + gameIdStart + "_" + gameIdEnd + ".csv";
    }

    @Test
    public void grabOneData() {
        String gameId = String.valueOf(id);
        String url = SOURCE_URL.replace("{gameId}", gameId);
        Response httpResponse = Http.get(url);
        if (httpResponse != null && httpResponse.isOK()) {
            System.out.println(httpResponse.getContent());
        }
    }

    @Test
    public void grabDataLoop() {
        String filePath = TEMP_PATh + fileName;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        CSVPrinter csvPrinter = null;
        try {
            fos = new FileOutputStream(filePath);
            osw = new OutputStreamWriter(fos, "GBK");

            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("gameId", "responseCode", "responseContent");
            csvPrinter = new CSVPrinter(osw, csvFormat);

            for (int i = gameIdStart; i < gameIdEnd + 1; i++) {
                long startTime = System.currentTimeMillis();
                String gameId = String.valueOf(i);
                String url = SOURCE_URL.replace("{gameId}", gameId);

                Response httpResponse = Http.get(url);
                if (httpResponse != null) {
                    csvPrinter.printRecord(gameId, httpResponse.getStatus(), httpResponse.getContent());
                } else {
                    csvPrinter.printRecord(gameId, "null", "");
                }
                System.out.println("GET " + url + " " + (httpResponse != null ? httpResponse.getStatus() : "null")
                        + " " + (System.currentTimeMillis() - startTime) + "ms");
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
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
