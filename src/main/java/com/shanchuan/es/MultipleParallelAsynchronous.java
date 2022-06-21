package com.shanchuan.es;

import org.apache.http.HttpEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

import java.util.concurrent.CountDownLatch;

public class MultipleParallelAsynchronous {
    public static void main(String[] args) throws InterruptedException {

        RestClient restClient = Client.getClient();

        //并行异步请求
        HttpEntity[] documents = new HttpEntity[10];
        CountDownLatch latch = new CountDownLatch(documents.length);
        for (int i = 0; i < documents.length; i++) {
            Request request = new Request("PUT", "/posts/doc/" + i);
            //let's assume that the documents are stored in an HttpEntity array
            request.setEntity(documents[i]);
            restClient.performRequestAsync(
                    request,
                    new ResponseListener() {
                        @Override
                        public void onSuccess(Response response) {

                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Exception exception) {

                            latch.countDown();
                        }
                    }
            );
        }
        latch.await();

    }
}
