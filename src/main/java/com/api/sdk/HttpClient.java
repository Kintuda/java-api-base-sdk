package com.api.sdk;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private OkHttpClient httpClient;

    private Map<String, String> defaultHeaders = new HashMap<>();

    public HttpClient(OkHttpClient client) {
        this.httpClient = client;
    }

    public HttpClient(List<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        for (Interceptor interceptor: interceptors) {
            builder.addInterceptor(interceptor);
        }

        this.defaultHeaders.put("Content-type", "application/json");

        this.httpClient = builder.connectTimeout(600, TimeUnit.SECONDS)
            .writeTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS).build();
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Map<String, String> getDefaultHeaders() {
        return defaultHeaders;
    }

    public void setDefaultHeaders(Map<String, String> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
    }
}
