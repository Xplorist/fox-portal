package com.foxconn.mcebg.portal.oidc.client;

import com.foxconn.mcebg.portal.oidc.client.model.IssuerServiceResponse;
import com.foxconn.mcebg.portal.oidc.client.service.AuthRequestOptionsService;
import com.foxconn.mcebg.portal.oidc.client.service.ClientConfigurationService;
import com.foxconn.mcebg.portal.oidc.client.service.IssuerService;
import com.foxconn.mcebg.portal.oidc.client.service.ServerConfigurationService;
import com.foxconn.mcebg.portal.oidc.client.service.impl.StaticAuthRequestOptionsService;
import com.foxconn.mcebg.portal.oidc.config.ServerConfiguration;
import com.foxconn.mcebg.portal.oidc.model.OIDCAuthenticationToken;
import com.foxconn.mcebg.portal.oidc.model.RegisteredClient;
import com.google.common.base.Strings;
import com.nimbusds.jose.util.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.Set;

public class OIDCLogoutSuccessHandler implements LogoutSuccessHandler {
//public class OIDCLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected final static String ISSUER_SESSION_VARIABLE = "issuer";
    protected final static String STATE_SESSION_VARIABLE = "state";
    protected final static int HTTP_SOCKET_TIMEOUT = 30000;
    protected int httpSocketTimeout = HTTP_SOCKET_TIMEOUT;

    @Autowired(required=false)
    private HttpClient httpClient;

    private IssuerService issuerService;
    private ServerConfigurationService serverConfigurationService;
    private ClientConfigurationService clientConfigurationService;
    private AuthRequestOptionsService authRequestOptionsService = new StaticAuthRequestOptionsService();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //String refererUrl = request.getHeader("Referer");
        //auditService.track("Logout from: " + refererUrl);

        String accessTokenValue = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof OIDCAuthenticationToken) {
            // if they're logging into this server from a remote OIDC server, pass through their user info
            OIDCAuthenticationToken oidc = (OIDCAuthenticationToken) auth;
            accessTokenValue = oidc.getAccessTokenValue();
        }

        HttpSession session = request.getSession();
        String storedState = getStoredState(session);

        IssuerServiceResponse issResp = issuerService.getIssuer(request);
        String issuer = null;
        if (issResp != null) {
            issuer = issResp.getIssuer();
        }

        if (!Strings.isNullOrEmpty(accessTokenValue) && !Strings.isNullOrEmpty(issuer)) {
            // pull the configurations based on that issuer
            ServerConfiguration serverConfig = serverConfigurationService.getServerConfiguration(issuer);
            final RegisteredClient clientConfig = clientConfigurationService.getClientConfiguration(serverConfig);

            if(httpClient == null) {
                httpClient = HttpClientBuilder.create()
                        .useSystemProperties()
                        .setDefaultRequestConfig(RequestConfig.custom()
                                .setSocketTimeout(httpSocketTimeout)
                                .build())
                        .build();
            }

            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

            RestTemplate restTemplate = new RestTemplate(factory) {

                @Override
                protected ClientHttpRequest createRequest(URI url, HttpMethod method) throws IOException {
                    ClientHttpRequest httpRequest = super.createRequest(url, method);
                    httpRequest.getHeaders().add("Authorization",
                            String.format("Basic %s", Base64.encode(String.format("%s:%s",
                                    UriUtils.encodePathSegment(clientConfig.getClientId(), "UTF-8"),
                                    UriUtils.encodePathSegment(clientConfig.getClientSecret(), "UTF-8")))));

                    return httpRequest;
                }
            };

            Set<String> redirectUris = clientConfig.getRedirectUris();
            String redirectUri = null;
            for (String uri: redirectUris) {
                if (uri.contains("openid_connect_login")) {
                    redirectUri = uri;
                }
            }

            String jsonString = null;
            try {
                //jsonString = restTemplate.postForObject(serverConfig.getTokenEndpointUri(), form, String.class);
                jsonString = restTemplate.getForObject(serverConfig.getIssuer() + "endsession?id_token_hint=" + accessTokenValue
                        + "&post_logout_redirect_uri=" + redirectUri + "&state=" + storedState, String.class);
                logger.info(jsonString);
            } catch (RestClientException e) {
                // Handle error
                logger.error("logout failed:  " + e.getMessage());

                throw new RuntimeException("Unable to logout: " + e.getMessage());
            }
        }

        //super.onLogoutSuccess(request, response, authentication);
    }

    private static String getStoredSessionString(HttpSession session, String key) {
        Object o = session.getAttribute(key);
        if (o != null && o instanceof String) {
            return o.toString();
        } else {
            return null;
        }
    }

    public IssuerService getIssuerService() {
        return issuerService;
    }

    public void setIssuerService(IssuerService issuerService) {
        this.issuerService = issuerService;
    }

    protected static String getStoredState(HttpSession session) {
        return getStoredSessionString(session, STATE_SESSION_VARIABLE);
    }

    public ServerConfigurationService getServerConfigurationService() {
        return serverConfigurationService;
    }

    public void setServerConfigurationService(ServerConfigurationService serverConfigurationService) {
        this.serverConfigurationService = serverConfigurationService;
    }

    public ClientConfigurationService getClientConfigurationService() {
        return clientConfigurationService;
    }

    public void setClientConfigurationService(ClientConfigurationService clientConfigurationService) {
        this.clientConfigurationService = clientConfigurationService;
    }

    public AuthRequestOptionsService getAuthRequestOptionsService() {
        return authRequestOptionsService;
    }

    public void setAuthRequestOptionsService(AuthRequestOptionsService authRequestOptionsService) {
        this.authRequestOptionsService = authRequestOptionsService;
    }
}
