package com.api.sdk;

import java.util.Map;

public class APIResponse<T> {
    private final int httpCode;

    private final String body;

    private final Map<String, String> headers;

    private final T data;

    public APIResponse(int httpCode, String body, Map<String, String> headers, T data) {
        this.httpCode = httpCode;
        this.body = body;
        this.headers = headers;
        this.data = data;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public T getData() {
        return data;
    }
}
