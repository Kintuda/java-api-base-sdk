package com.api.sdk;

import com.api.sdk.constants.HttpMethods;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;
import okhttp3.internal.http.HttpMethod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private static final String JSON_CONTENT_TYPE = "application/json";

    private static final String CONTENT_TYPE = "Content-Type";
    private final Serializer serializer = new Serializer();
    private final OkHttpClient client;
    private URL basePath;
    private Map<String, String> defaultHeaders = new HashMap<>();

    public HttpClient(OkHttpClient client) {
        this.client = client;
    }

    public HttpClient(List<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        this.defaultHeaders.put(CONTENT_TYPE, JSON_CONTENT_TYPE);
        this.client = builder
            .connectTimeout(600, TimeUnit.SECONDS)
            .writeTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .build();
    }

    public Call buildRequest(@NotNull String path, String method, Object body, @Nullable Map<String, String> headerParams, @Nullable Map<String, String> queryStringParams) throws JsonProcessingException, MalformedURLException, UnsupportedEncodingException {
        RequestBody requestBody;
        Map<String, String> headers = new HashMap<>(defaultHeaders);

        if (headerParams != null) {
            headerParams.forEach((key, value) -> headers.merge(key, value, (v1, v2) -> v1.equalsIgnoreCase(v2) ? v1 : v1 + ", " + v2));
        }

        HttpUrl url = this.buildUrl(path, queryStringParams);
        String contentType = headers.get(CONTENT_TYPE);

        if (!HttpMethod.permitsRequestBody(method)) {
            requestBody = null;
        } else if (body == null) {
            if (HttpMethods.DELETE.equals(method)) {
                requestBody = null;
            } else {
                requestBody = RequestBody.create("", MediaType.parse(contentType));
            }
        } else {
            requestBody = serialize(body, contentType);
        }


        final Request.Builder reqBuilder = new Request.Builder().url(url);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            reqBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = reqBuilder.method(method, requestBody).build();
        return client.newCall(request);
    }

    public HttpUrl buildUrl(String path, Map<String, String> queryParams) throws UnsupportedEncodingException {
        HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(basePath.toString()))
            .newBuilder()
            .addPathSegment(path);

        if (queryParams == null) {
            return httpBuilder.build();
        }

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            String key = URLEncoder.encode(entry.getKey(), "UTF-8");
            String value = URLEncoder.encode(entry.getValue(), "UTF-8");
            httpBuilder.addQueryParameter(key, value);
        }

        return httpBuilder.build();

    }

    public RequestBody serialize(Object obj, String contentType) throws JsonProcessingException {
        if (obj instanceof byte[]) {
            return RequestBody.create((byte[]) obj, MediaType.parse(contentType));
        } else if (obj instanceof File) {
            return RequestBody.create((File) obj, MediaType.parse(contentType));
        } else if ("text/plain".equals(contentType) && obj instanceof String) {
            return RequestBody.create((String) obj, MediaType.parse(contentType));
        } else {
            String content;
            if (obj != null) {
                content = serializer.classToString(obj);
            } else {
                content = null;
            }
            return RequestBody.create(content, MediaType.parse(contentType));
        }
    }

    public <T> T deserialize(Response response, Class<T> clazz) throws SDKException, JsonProcessingException {
        if (response == null || clazz == null) {
            return null;
        }

        if ("byte[]".equals(clazz.toString())) {
            try {
                return (T) response.body().bytes();
            } catch (IOException e) {
                throw new SDKException(e);
            }
        }

        String respBody;
        try {
            if (response.body() != null)
                respBody = response.body().string();
            else
                respBody = null;
        } catch (IOException e) {
            throw new SDKException(e);
        }

        if (respBody == null || "".equals(respBody)) {
            return null;
        }

        String contentType = response.headers().get(CONTENT_TYPE);

        if (contentType == null) {
            contentType = JSON_CONTENT_TYPE;
        }

        if (contentType.equals(JSON_CONTENT_TYPE)) {
            return serializer.stringToClass(clazz, respBody);
        } else {
            throw new SDKException(
                String.format("Content type %s not supported for type", contentType),
                response.code(),
                response.headers().toMultimap(),
                respBody);
        }
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Map<String, String> getDefaultHeaders() {
        return defaultHeaders;
    }

    public void setDefaultHeaders(Map<String, String> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
    }

    public URL getBasePath() {
        return basePath;
    }

    public void setBasePath(URL basePath) {
        this.basePath = basePath;
    }
}
