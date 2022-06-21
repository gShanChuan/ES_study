package com.shanchuan.es;

import org.elasticsearch.client.*;

import java.io.IOException;

public class AsynchronousRequest {
    public static void main(String[] args) throws IOException {
        //异步请求
        RestClient restClient = Client.getClient();


        Request request = new Request(
                "GET",
                "/");
        Cancellable cancellable = restClient.performRequestAsync(request,
                new ResponseListener() {
                    @Override
                    public void onSuccess(Response response) {

                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });

        restClient.close();
    }
}
