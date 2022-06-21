package com.shanchuan.es;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

public class Client {

    public static RestClient getClient() {
        //获取连接
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")).build();
        return restClient;
    }

    //设置带默认请求头的客户端
    public static RestClient getClientWithDefaultHeaders() {
        //获取连接
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));
        Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
        builder.setDefaultHeaders(defaultHeaders);

        RestClient restClient = builder.build();
        return restClient;
    }

    //设置带节点监听器的客户端
    public static RestClient getClientWithListene() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));
        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {

            }
        });

        RestClient restClient = builder.build();
        return restClient;
    }

    //设置带节点选择器的客户端
    public static RestClient getClientWithSelector() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));
        builder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);

        RestClient restClient = builder.build();
        return restClient;
    }


    //设置请求回调的客户端
    public static RestClient getClientWithRequestConfigCallback() {

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));

        builder.setRequestConfigCallback(
                new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(
                            RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setSocketTimeout(10000);
                    }
                });

        RestClient restClient = builder.build();
        return restClient;

    }

    // 设置客户端回调可以通过在通过其构建器构建 RestClient 时提供 RequestConfigCallback 实例来配置请求超时。
    // 该接口有一个方法接收 org.apache.http.client.config.RequestConfig.Builder 的实例作为参数，并具有相同的返回类型。
    // 可以修改请求配置生成器，然后返回。

    public static RestClient getClientWithHttpClientConfigCallback() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(
                    HttpAsyncClientBuilder httpClientBuilder) {
                return httpClientBuilder.setProxy(
                        new HttpHost("proxy", 9000, "http"));
            }
        });

        RestClient restClient = builder.build();
        return restClient;
    }


}
