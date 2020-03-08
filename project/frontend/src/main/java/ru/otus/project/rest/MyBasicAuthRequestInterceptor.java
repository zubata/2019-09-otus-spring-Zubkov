package ru.otus.project.rest;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Util;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class MyBasicAuthRequestInterceptor implements RequestInterceptor {
    private String username;
    private String password;

    @Override
    public void apply(RequestTemplate template) {
        String myHeaderValue = "Basic " + base64Encode((username + ":" + password).getBytes(Util.ISO_8859_1));
        template.header("Authorization", new String[]{myHeaderValue});
    }

    private static String base64Encode(byte[] bytes) {
        return Base64Utils.encodeToString(bytes);
    }

    public void setUser(String name, String password) {
        this.username=name;
        this.password=password;
    }
}

