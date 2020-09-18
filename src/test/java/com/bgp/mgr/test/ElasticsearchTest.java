package com.bgp.mgr.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bgp.mgr.service.ElasticsearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {

    @Resource
    private ElasticsearchService elasticsearchService;

    private String indexName;

    @Before
    public void init() {
        indexName = "bgg_game_info";
    }

    @Test
    public void testCreateIndex() {
        String mappingJson = "";
        elasticsearchService.createIndex(indexName, mappingJson);
    }

    @Test
    public void testExistsIndex() {
        elasticsearchService.existsIndex(indexName);
    }

    @Test
    public void testDeleteIndex() {
        elasticsearchService.deleteIndex(indexName);
    }

    @Test
    public void testAdd() {
        String jsonString = "";
        String id = "";
        elasticsearchService.add(indexName, JSON.parse(jsonString), id);
    }

    @Test
    public void testGet() {
        String id = "1";
        JSONObject obj = elasticsearchService.get(indexName, id, JSONObject.class);
        System.out.println(obj.toJSONString());
    }
}
