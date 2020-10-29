package com.t0m4uk1991.sundata.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiClientWrapper <T>{
    private OkHttpClient client;
    private ObjectMapper objectMapper;
    private Class<T> type;

    public ApiClientWrapper(Class<T> type) {
        client = new OkHttpClient().newBuilder()
                .addInterceptor(new ResponseNormalizationInterceptor())
                .build();
        objectMapper = new ObjectMapper();
        this.type = type;
    }

    public T execute(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "OkHttp Headers.java")
                .build();
        final Response response = client.newCall(request)
                .execute();

        return objectMapper.readValue(response.body().byteStream(), type);
    }
}
