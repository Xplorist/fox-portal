package com.foxconn.mcebg.portal.oidc.client.service;

import com.foxconn.mcebg.portal.oidc.config.ServerConfiguration;
import com.foxconn.mcebg.portal.oidc.model.RegisteredClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AuthRequestOptionsService {
    /**
     * The set of options needed at the authorization endpoint.
     *
     * @param server
     * @param client
     * @param request
     * @return
     */
    public Map<String, String> getOptions(ServerConfiguration server, RegisteredClient client, HttpServletRequest request);

    /**
     * The set of options needed at the token endpoint.
     *
     * @param server
     * @param client
     * @param request
     * @return
     */
    public Map<String, String> getTokenOptions(ServerConfiguration server, RegisteredClient client, HttpServletRequest request);
}
