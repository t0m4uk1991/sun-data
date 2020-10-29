package com.t0m4uk1991.sundata.api;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

public class ResponseNormalizationInterceptor implements Interceptor {

    private final static Logger LOGGER = Logger.getLogger(ResponseNormalizationInterceptor.class.getName());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        LOGGER.info(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);
        String jsonData = response.body().string();
        JSONObject responsePayload = new JSONObject(jsonData);
        JSONObject data = responsePayload.getJSONObject("results");

        long t2 = System.nanoTime();
        LOGGER.info(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response.newBuilder()
                .body(ResponseBody.create(data.toString(), MediaType.parse("application/json")))
                .build();
    }
}
