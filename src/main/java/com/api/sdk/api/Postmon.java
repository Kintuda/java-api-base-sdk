package com.api.sdk.api;

import com.api.sdk.HttpClient;
import com.api.sdk.model.PostmonResponseDTO;

public class Postmon {
    private final HttpClient httpClient;

    public Postmon(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public PostmonResponseDTO getCepInformation(String cep) {
        this.httpClient.
    }
}

