package com.foxconn.mcebg.portal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oidc")
@PropertySource(value = "classpath:configuration/oidc.properties")
public class OIDCPropertiesConfig {
    private String subject;
    private String serverUrl;
    private String clientUrl;
    private String clientId;
    private String clientSecret;
}
