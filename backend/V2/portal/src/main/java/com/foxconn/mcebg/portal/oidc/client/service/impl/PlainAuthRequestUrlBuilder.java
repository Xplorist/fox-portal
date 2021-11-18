package com.foxconn.mcebg.portal.oidc.client.service.impl;

import com.foxconn.mcebg.portal.oidc.client.service.AuthRequestUrlBuilder;
import com.foxconn.mcebg.portal.oidc.config.ServerConfiguration;
import com.foxconn.mcebg.portal.oidc.model.RegisteredClient;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.security.authentication.AuthenticationServiceException;

import java.net.URISyntaxException;
import java.util.Map;

public class PlainAuthRequestUrlBuilder implements AuthRequestUrlBuilder {
    @Override
    public String buildAuthRequestUrl(ServerConfiguration serverConfig, RegisteredClient clientConfig, String redirectUri, String nonce, String state, Map<String, String> options, String loginHint) {
        try {

            URIBuilder uriBuilder = new URIBuilder(serverConfig.getAuthorizationEndpointUri());
            uriBuilder.addParameter("response_type", "code");
            uriBuilder.addParameter("client_id", clientConfig.getClientId());
            uriBuilder.addParameter("scope", Joiner.on(" ").join(clientConfig.getScope()));

            uriBuilder.addParameter("redirect_uri", redirectUri);

            uriBuilder.addParameter("nonce", nonce);

            uriBuilder.addParameter("state", state);

            // Optional parameters:
            for (Map.Entry<String, String> option : options.entrySet()) {
                uriBuilder.addParameter(option.getKey(), option.getValue());
            }

            // if there's a login hint, send it
            if (!Strings.isNullOrEmpty(loginHint)) {
                uriBuilder.addParameter("login_hint", loginHint);
            }

            return uriBuilder.build().toString();

        } catch (URISyntaxException e) {
            throw new AuthenticationServiceException("Malformed Authorization Endpoint Uri", e);

        }

    }
}
