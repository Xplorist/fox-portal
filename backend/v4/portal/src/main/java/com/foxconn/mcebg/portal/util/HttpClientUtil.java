package com.foxconn.mcebg.portal.util;

import com.foxconn.mcebg.portal.oidc.model.DefaultUserInfo;
import com.foxconn.mcebg.portal.oidc.model.UserInfo;
import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

@Slf4j
public class HttpClientUtil {
    private static HttpClient httpClient;
    private static HttpComponentsClientHttpRequestFactory factory;

    static {
        httpClient = HttpClientBuilder.create().useSystemProperties().build();
        factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    // 获取UserInfo
    public static UserInfo getUserInfo(String access_token) throws Exception {
        String userInfoString = null;

        RestTemplate restTemplate = new RestTemplate(factory) {
            @Override
            protected ClientHttpRequest createRequest(URI url, HttpMethod method) throws IOException {
                ClientHttpRequest httpRequest = super.createRequest(url, method);
                httpRequest.getHeaders().add("Authorization", String.format("Bearer %s", access_token));
                return httpRequest;
            }
        };
        userInfoString = restTemplate.getForObject("http://10.244.168.180/openid/userinfo", String.class);

        if (!Strings.isNullOrEmpty(userInfoString)) {
            JsonObject userInfoJson = new JsonParser().parse(userInfoString).getAsJsonObject();
            UserInfo userInfo = fromJson(userInfoJson);

            return userInfo;
        } else {
            // didn't get anything throw exception
            throw new IllegalArgumentException("Unable to load user info");
        }
    }

    protected static UserInfo fromJson(JsonObject userInfoJson) {
        return DefaultUserInfo.fromJson(userInfoJson);
    }
}
