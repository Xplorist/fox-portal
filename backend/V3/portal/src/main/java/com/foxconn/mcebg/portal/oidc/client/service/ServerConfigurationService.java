package com.foxconn.mcebg.portal.oidc.client.service;

import com.foxconn.mcebg.portal.oidc.config.ServerConfiguration;

public interface ServerConfigurationService {
    ServerConfiguration getServerConfiguration(String issuer);
}
