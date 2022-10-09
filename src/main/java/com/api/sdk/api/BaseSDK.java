package com.api.sdk.api;

import com.api.sdk.HttpClient;

import java.net.URL;
import java.util.Collections;

public final class BaseSDK {
    private final HttpClient client;

    private Postmon postmon;

    public BaseSDK(URL basePath) {
        this.client = new HttpClient(Collections.emptyList());
        this.client.setBasePath(basePath);
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
