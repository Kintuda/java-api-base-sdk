package com.api.sdk;

import com.api.sdk.api.BaseSDK;
import com.api.sdk.model.PostmonCEPResponse;

import java.io.IOException;
import java.net.URL;

public class main {
    public static void main(String[] args) throws IOException, SDKException {
        URL url = new URL("https://api.postmon.com.br/v1");
        BaseSDK sdk = new BaseSDK(url);
        PostmonCEPResponse response = sdk.getPostmon().getCepInformation("87045430");
        System.out.println(response.getCidade());
    }
}
