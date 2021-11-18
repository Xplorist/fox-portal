package com.foxconn.mcebg.portal.oidc.client.service;

import com.foxconn.mcebg.portal.oidc.config.ServerConfiguration;
import com.foxconn.mcebg.portal.oidc.model.RegisteredClient;

import java.util.Map;

public interface AuthRequestUrlBuilder {
    public String buildAuthRequestUrl(ServerConfiguration serverConfig, RegisteredClient clientConfig, String redirectUri, String nonce, String state, Map<String, String> options, String loginHint);
}
