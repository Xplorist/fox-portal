package com.foxconn.mcebg.portal.oidc.client.service;

import com.foxconn.mcebg.portal.oidc.config.ServerConfiguration;
import com.foxconn.mcebg.portal.oidc.model.RegisteredClient;

public interface ClientConfigurationService {
    RegisteredClient getClientConfiguration(ServerConfiguration issuer);
}
