package com.bgp.mgr.service;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Collection;
import java.util.List;

public interface ElasticsearchService {

    /**
     * 创建index
     *
     * @param idxName
     * @param mappingJson
     * @return
     */
    boolean createIndex(String idxName, String mappingJson);

    /**
     * 判断index是否存在
     *
     * @param idxName
     * @return
     */
    boolean existsIndex(String idxName);

    /**
     * 删除index
     *
     * @param idxName
     * @return
     */
    boolean deleteIndex(String idxName);

    /**
     * 插入document（不指定id）
     *
     * @param idxName
     * @param o
     * @return
     */
    String add(String idxName, Object o);

    /**
     * 插入document
     *
     * @param idxName
     * @param o
     * @param id
     * @return
     */
    String add(String idxName, Object o, String id);

    /**
     * 更新document
     *
     * @param idxName
     * @param o
     * @param id
     * @return
     */
    boolean update(String idxName, Object o, String id);

    /**
     * 批量删除document
     *
     * @param idxName
     * @param idList
     * @return
     */
    void deleteBatch(String idxName, Collection<String> idList);

    /**
     * 条件删除document
     *
     * @param idxName
     * @param builder
     */
    void deleteByQuery(String idxName, QueryBuilder builder);

    /**
     * 校验存在document
     *
     * @param idxName
     * @param id
     * @return
     */
    boolean exists(String idxName, String id);

    /**
     * 获取document
     *
     * @param idxName
     * @param id
     * @return
     */
    JSONObject get(String idxName, String id);

    /**
     * 获取document
     *
     * @param idxName
     * @param id
     * @param tClass
     * @return
     */
    <T> T get(String idxName, String id, Class<T> tClass);

    /**
     * 条件查询document
     *
     * @param idxName
     * @param builder
     * @param tClass
     * @param <T>
     * @return
     */
    <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> tClass);
}
