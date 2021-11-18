package com.foxconn.mcebg.portal.config;

import com.foxconn.mcebg.portal.oidc.client.service.*;
import com.foxconn.mcebg.portal.oidc.client.service.impl.*;
import com.foxconn.mcebg.portal.oidc.config.ServerConfiguration;
import com.foxconn.mcebg.portal.oidc.model.ClientDetailsEntity;
import com.foxconn.mcebg.portal.oidc.model.RegisteredClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class OIDCConfig {
    @Value("${oidc.serverUrl}")
    private String serverUrl;

    @Value("${oidc.clientUrl}")
    private String clientUrl;

    @Value("${oidc.clientId}")
    private String clientId;

    @Value("${oidc.clientSecret}")
    private String clientSecret;

    @Value("${oidc.subject}")
    private String subject;

    @Bean
    public IssuerService issuerService() {
        IssuerService service = staticSingleIssuerService();

        return service;
    }

    @Bean
    public StaticSingleIssuerService staticSingleIssuerService() {
        StaticSingleIssuerService service = new StaticSingleIssuerService();
        service.setIssuer(serverUrl);

        return service;
    }

    @Bean
    public ServerConfigurationService serverConfigurationService() {
        ServerConfigurationService service = staticServerConfigurationService();

        return service;
    }

    @Bean
    public StaticServerConfigurationService staticServerConfigurationService() {
        StaticServerConfigurationService service = new StaticServerConfigurationService();
        Map<String, ServerConfiguration> servers = new HashMap<>();

        ServerConfiguration conf = new ServerConfiguration();
        conf.setIssuer(serverUrl);
        conf.setAuthorizationEndpointUri(serverUrl + "authorize");
        conf.setTokenEndpointUri(serverUrl + "token");
        conf.setUserInfoUri(serverUrl + "userinfo");
        conf.setJwksUri(serverUrl + "jwk");

        servers.put(serverUrl, conf);

        service.setServers(servers);

        return service;
    }

    @Bean
    public ClientConfigurationService clientConfigurationService() {
        ClientConfigurationService service = staticClientConfigurationService();

        return service;
    }

    @Bean
    public StaticClientConfigurationService staticClientConfigurationService() {
        StaticClientConfigurationService service = new StaticClientConfigurationService();

        Map<String, RegisteredClient> clients = new HashMap<>();

        RegisteredClient client = new RegisteredClient();
        client.setClientId(clientId);
        client.setClientSecret(clientSecret);
        Set<String> scope = new HashSet<>();
        scope.add("openid");
        scope.add("email");
        scope.add("address");
        scope.add("profile");
        scope.add("phone");
        client.setScope(scope);
        client.setTokenEndpointAuthMethod(ClientDetailsEntity.AuthMethod.SECRET_BASIC);
        Set<String> redirectUris = new HashSet<>();
        //redirectUris.add("http://10.244.168.180/portal/openid_connect_login");
        redirectUris.add(clientUrl + "openid_connect_login");
        client.setRedirectUris(redirectUris);

        clients.put(serverUrl, client);

        service.setClients(clients);

        return service;
    }

    @Bean
    public AuthRequestOptionsService authRequestOptionsService() {
        AuthRequestOptionsService service = staticAuthRequestOptionsService();

        return service;
    }

    @Bean
    public StaticAuthRequestOptionsService staticAuthRequestOptionsService() {
        StaticAuthRequestOptionsService service = new StaticAuthRequestOptionsService();

        return service;
    }

    @Bean
    public AuthRequestUrlBuilder authRequestUrlBuilder() {
        AuthRequestUrlBuilder builder = plainAuthRequestUrlBuilder();

        return builder;
    }

    @Bean
    public PlainAuthRequestUrlBuilder plainAuthRequestUrlBuilder() {
        PlainAuthRequestUrlBuilder builder = new PlainAuthRequestUrlBuilder();

        return builder;
    }

}
