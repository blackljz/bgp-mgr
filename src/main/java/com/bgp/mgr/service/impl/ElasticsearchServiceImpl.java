package com.bgp.mgr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bgp.mgr.common.exception.BgpException;
import com.bgp.mgr.service.ElasticsearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("elasticsearchService")
public class ElasticsearchServiceImpl implements ElasticsearchService {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);

    private static final int INDEX_NUMBER_OF_SHARDS = 5;
    private static final int INDEX_NUMBER_OF_REPLICAS = 1;

    @Resource
    private RestHighLevelClient elasticsearchClient;


    @Override
    public boolean createIndex(String idxName, String mappingJson) {
        try {
            if (this.existsIndex(idxName)) {
                logger.error("待创建索引 indexName={} 已经存在", idxName);
                return true;
            }
            CreateIndexRequest request = new CreateIndexRequest(idxName)
                    .settings(Settings.builder() //设置分片
                            .put("index.number_of_shards", INDEX_NUMBER_OF_SHARDS)
                            .put("index.number_of_replicas", INDEX_NUMBER_OF_REPLICAS));
            if (StringUtils.isNotBlank(mappingJson)) {
                request.mapping(mappingJson, XContentType.JSON);
            }
            CreateIndexResponse res = elasticsearchClient.indices().create(request, RequestOptions.DEFAULT);
            logger.debug("result={}", JSON.toJSONString(res));
            return res.isAcknowledged();
        } catch (Exception e) {
            logger.error("索引 indexName={} 创建异常", idxName, e);
            throw new BgpException("创建索引异常");
        }
    }

    @Override
    public boolean existsIndex(String idxName) {
        try {
            GetIndexRequest request = new GetIndexRequest(idxName);
            return elasticsearchClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error("索引 indexName={} 校验存在异常", idxName, e);
            throw new BgpException("校验索引存在异常");
        }
    }

    @Override
    public boolean deleteIndex(String idxName) {
        try {
            if (!this.existsIndex(idxName)) {
                logger.error("待删除索引 idxName={} 不存在", idxName);
                return true;
            }
            AcknowledgedResponse res = elasticsearchClient.indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
            return res.isAcknowledged();
        } catch (Exception e) {
            logger.error("索引 indexName={} 删除异常", idxName, e);
            throw new BgpException("删除索引异常");
        }
    }

    @Override
    public String add(String idxName, Object o) {
        return this.add(idxName, o, null);
    }

    @Override
    public String add(String idxName, Object o, String id) {
        try {
            String jsonString;
            if (o instanceof String) {
                jsonString = (String) o;
            } else {
                jsonString = JSON.toJSONString(o);
            }
            IndexRequest indexRequest = new IndexRequest(idxName).id(id).source(jsonString, XContentType.JSON);
            if (id != null) {
                indexRequest.id(id);
            }
            IndexResponse indexResponse = elasticsearchClient.index(indexRequest, RequestOptions.DEFAULT);
            logger.debug("add result={}", JSON.toJSONString(indexResponse));
            return indexResponse.getId();
        } catch (Exception e) {
            logger.error("索引 indexName={} 插入文档 id={}异常", idxName, id, e);
            throw new BgpException("插入文档异常");
        }
    }

    @Override
    public boolean update(String idxName, Object o, String id) {
        try {
            String jsonString;
            if (o instanceof String) {
                jsonString = (String) o;
            } else {
                jsonString = JSON.toJSONString(o);
            }
            UpdateRequest request = new UpdateRequest(idxName, id).doc(jsonString, XContentType.JSON);
            UpdateResponse updateResponse = elasticsearchClient.update(request, RequestOptions.DEFAULT);
            logger.debug("update result={}", JSON.toJSONString(updateResponse));
            return true;
        } catch (Exception e) {
            logger.error("索引 indexName={} 更新文档 id={} 异常", idxName, id, e);
            throw new BgpException("更新文档异常");
        }
    }

//    public void insertBatch(String idxName, List<ElasticEntity> list) {
//        try {
//            BulkRequest bulkRequest = new BulkRequest();
//            list.forEach(item -> bulkRequest.add(new IndexRequest(idxName).id(item.getId())
//                    .source(item.getData(), XContentType.JSON)));
//            elasticsearchClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void deleteBatch(String idxName, Collection<String> idList) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            idList.forEach(id -> bulkRequest.add(new DeleteRequest(idxName, id)));
            elasticsearchClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error("索引 indexName={} 批量删除文档 idList={} 异常", idxName, JSON.toJSONString(idList), e);
            throw new BgpException("批量删除文档异常");
        }
    }

    @Override
    public void deleteByQuery(String idxName, QueryBuilder builder) {
        try {
            DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(idxName);
            deleteByQueryRequest.setQuery(builder);
            deleteByQueryRequest.setBatchSize(10000);// 设置批量操作数量,最大为10000
            deleteByQueryRequest.setConflicts("proceed");
            elasticsearchClient.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error("索引 indexName={} 条件删除文档 query={} 异常", idxName, builder.toString(), e);
            throw new BgpException("条件删除文档异常");
        }
    }

    @Override
    public boolean exists(String idxName, String id) {
        try {
            GetRequest getRequest = new GetRequest(idxName).id(id);
            return elasticsearchClient.exists(getRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error("索引 indexName={} 校验存在文档 id={} 异常", idxName, id, e);
            throw new BgpException("校验文档存在异常");
        }
    }

    @Override
    public JSONObject get(String idxName, String id) {
        return this.get(idxName, id, JSONObject.class);
    }

    @Override
    public <T> T get(String idxName, String id, Class<T> tClass) {
        try {
            GetRequest getRequest = new GetRequest(idxName).id(id);
            GetResponse getResponse = elasticsearchClient.get(getRequest, RequestOptions.DEFAULT);
            logger.info("get result={}", JSON.toJSONString(getResponse));
            return JSON.parseObject(getResponse.getSourceAsString(), tClass);
        } catch (Exception e) {
            logger.error("索引 indexName={} 获取文档异常", idxName, e);
            throw new BgpException("获取文档异常");
        }
    }

    @Override
    public <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> tClass) {
        SearchRequest searchRequest = new SearchRequest(idxName);
        searchRequest.source(builder);
        try {
            SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<T> result = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                result.add(JSON.parseObject(hit.getSourceAsString(), tClass));
            }
            return result;
        } catch (Exception e) {
            logger.error("索引 indexName={} 条件查询文档 query={} 异常", idxName, builder.toString(), e);
            throw new BgpException("条件查询文档异常");
        }
    }

}
