package com.api.sdk;

import java.util.Map;
import java.util.List;

public class SDKException extends Exception {
    private int code = 0;
    private Map<String, List<String>> responseHeaders = null;
    private String responseBody = null;

    public SDKException() {
    }

    public SDKException(Throwable throwable) {
        super(throwable);
    }

    public SDKException(String message) {
        super(message);
    }

    public SDKException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        super(message, throwable);
        this.code = code;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }

    public SDKException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
        this(message, null, code, responseHeaders, responseBody);
    }

    public SDKException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders) {
        this(message, throwable, code, responseHeaders, null);
    }

    public SDKException(int code, Map<String, List<String>> responseHeaders, String responseBody) {
        this(null, null, code, responseHeaders, responseBody);
    }

    public SDKException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SDKException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
        this(code, message);
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }

    public int getCode() {
        return code;
    }


    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }


    public String getResponseBody() {
        return responseBody;
    }
}
