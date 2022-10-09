package com.api.sdk.api;

import com.api.sdk.HttpClient;
import com.api.sdk.SDKException;
import com.api.sdk.model.PostmonCEPResponse;
import okhttp3.Call;
import okhttp3.Response;

import java.io.IOException;

public class Postmon {
    private final HttpClient httpClient;

    public Postmon(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public PostmonCEPResponse getCepInformation(String cep) throws IOException, SDKException {
        Call request = this.httpClient.buildRequest(String.format("cep/%s", cep), "GET", null, null);
        Response response = request.execute();

        if (response.isSuccessful()) {
            return httpClient.deserialize(response, PostmonCEPResponse.class);
        }

        throw new SDKException(response.code(), response.message(), response.headers().toMultimap(), response.body().string());
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }
}

