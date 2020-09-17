package com.bgp.mgr.config;


import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Objects;

@Configuration
public class ElasticsearchConfig implements EnvironmentAware {

    private String hosts;// 集群地址，多个用,隔开
    private int port;// 使用的端口号
    private String scheme;// 使用的协议
    private String securityUser;// ES鉴权用户名
    private String securityPassword;// ES鉴权密码

    private static int connectTimeOut = 1000; // 连接超时时间
    private static int socketTimeOut = 30000; // 连接超时时间
    private static int connectionRequestTimeOut = 500; // 获取连接的超时时间

    private static int maxConnectNum = 100; // 最大连接数
    private static int maxConnectPerRoute = 100; // 最大路由连接数

    private RestClientBuilder builder;

    @Override
    public void setEnvironment(Environment environment) {
        hosts = Objects.requireNonNull(environment.getProperty("es.host"));
        port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("es.port")));
        scheme = Objects.requireNonNull(environment.getProperty("es.scheme"));
        securityUser = environment.getProperty("es.user");
        securityPassword = environment.getProperty("es.password");
    }

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ArrayList<HttpHost> hostList = new ArrayList<>();
        String[] hostArray = hosts.split(",");
        for (String host : hostArray) {
            hostList.add(new HttpHost(host, port, scheme));
        }
        builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        this.setConnectTimeOutConfig();
        this.setMultiConnectConfig();
        return new RestHighLevelClient(builder);
    }

    /**
     * 异步httpclient的连接延时配置
     */
    private void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
    }

    /**
     * 异步httpclient的连接数配置
     */
    private void setMultiConnectConfig() {
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            if (StringUtils.isNotBlank(securityUser) && StringUtils.isNotBlank(securityPassword)) {
                httpClientBuilder.setDefaultCredentialsProvider(this.initAuth());
            }
            return httpClientBuilder;
        });
    }

    /**
     * 初始化ES鉴权
     *
     * @return
     */
    private CredentialsProvider initAuth() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(securityUser, securityPassword));
        return credentialsProvider;
    }

}
