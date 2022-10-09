package com.api.sdk;

import okhttp3.*;
import okhttp3.internal.http.HttpMethod;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private OkHttpClient httpClient;

    private String basePath;

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

    public Request buildRequest(String path, String method, Object body, Map<String, String> headerParams) throws ApiException {
        RequestBody reqBody;
        String contentType = headerParams.get("Content-Type");

        if (!HttpMethod.permitsRequestBody(method)) {
            reqBody = null;
        } else if (body == null) {
            if ("DELETE".equals(method)) {
                // allow calling DELETE without sending a request body
                reqBody = null;
            } else {
                // use an empty request body (for POST, PUT and PATCH)
                reqBody = RequestBody.create("", MediaType.parse(contentType));
            }
        } else {
            reqBody = serialize(body, contentType);
        }

        final Request.Builder reqBuilder = new Request.Builder().url(url);

        return reqBuilder.method(method, reqBody).build();
    }

    public RequestBody serialize(Object obj, String contentType) throws ApiException {
        if (obj instanceof byte[]) {
            // Binary (byte array) body parameter support.
            return RequestBody.create((byte[]) obj, MediaType.parse(contentType));
        } else if (obj instanceof File) {
            // File body parameter support.
            return RequestBody.create((File) obj, MediaType.parse(contentType));
        } else if ("text/plain".equals(contentType) && obj instanceof String) {
            return RequestBody.create((String) obj, MediaType.parse(contentType));
        } else if (isJsonMime(contentType)) {
            String content;
            if (obj != null) {
                content = json.serialize(obj);
            } else {
                content = null;
            }
            return RequestBody.create(content, MediaType.parse(contentType));
        } else {
//            throw new ApiException("Content type \"" + contentType + "\" is not supported");
        }
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

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void addNewDefaultHeaders() {

    }

}
