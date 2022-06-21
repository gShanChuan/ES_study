package com.shanchuan.es;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;

import java.io.IOException;

public class SynchronousRequest {

    //抽取request相同的部分为RequestOptions
//    private static final RequestOptions COMMON_OPTIONS;
//    static {
//        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader("Authorization", "Bearer " + TOKEN);
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory
//                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
//        COMMON_OPTIONS = builder.build();
//    }

    public static void main(String[] args) throws IOException {

        RestClient restClient = Client.getClient();

        //同步请求
        Request request = new Request(
                "GET",
                "/");

//        //添加请求参数
//        request.addParameter("pretty", "true");
//
//        //设置请求体post
//        request.setEntity(new NStringEntity(
//                "{\"json\":\"text\"}",
//                ContentType.APPLICATION_JSON));
//        //或
//        request.setJsonEntity("{\"json\":\"text\"}");
//
//        //设置RequestOptions
//        request.setOptions(COMMON_OPTIONS);
//        //或者重新设置RequestOptions中的公共部分
//        RequestOptions.Builder options = COMMON_OPTIONS.toBuilder();
//        options.addHeader("cats", "knock things off of other things");
//        request.setOptions(options);


        Response response = restClient.performRequest(request);

        //读取响应
        RequestLine requestLine = response.getRequestLine();
        HttpHost host = response.getHost();
        int statusCode = response.getStatusLine().getStatusCode();
        Header[] headers = response.getHeaders();
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);

        restClient.close();
    }
}
