package ru.otus.project.rest;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class MyBasicAuthRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String TOKEN_TYPE = "Bearer";
    private final String clientId;
    private final String clientSecret;
    private String token;

    public MyBasicAuthRequestInterceptor(@Value("${security.oauth2.client.clientId}") String id,
                                         @Value("${security.oauth2.client.clientSecret}") String secret) {
        this.clientId = id;
        this.clientSecret = secret;
    }

    @Override
    public void apply(RequestTemplate template) {
        if(token == null) {
            String myHeaderValue = "Basic " + base64Encode((clientId + ":" + clientSecret).getBytes(Util.ISO_8859_1));
            template.header("Authorization", new String[]{myHeaderValue});
        }
        String myHeaderValue = "Bearer " + token;
        template.header("Authorization", new String[]{myHeaderValue});
    }

    private static String base64Encode(byte[] bytes) {
        return Base64Utils.encodeToString(bytes);
    }

    public void setToken(String token) {
        this.token = token;
    }
}

