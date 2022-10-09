package com.api.sdk.api;

import com.api.sdk.HttpClient;
import okhttp3.Interceptor;

import java.util.Collections;
import java.util.List;

public final class BaseSDK {
    private final HttpClient client;

    private Postmon postmon;

    public BaseSDK() {
        this.client = new HttpClient(Collections.<Interceptor>emptyList());
        this.init();
    }

    private void init() {
        this.postmon = new Postmon(this.client);
    }

    public HttpClient getClient() {
        return client;
    }

    public Postmon getPostmon() {
        return postmon;
    }

    public void setPostmon(Postmon postmon) {
        this.postmon = postmon;
    }
}
