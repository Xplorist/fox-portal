package com.foxconn.mcebg.portal.interceptor;

import com.foxconn.mcebg.portal.oidc.client.service.AuthRequestOptionsService;
import com.foxconn.mcebg.portal.oidc.client.service.ClientConfigurationService;
import com.foxconn.mcebg.portal.oidc.client.service.IssuerService;
import com.foxconn.mcebg.portal.oidc.client.service.ServerConfigurationService;
import com.foxconn.mcebg.portal.oidc.client.service.impl.StaticAuthRequestOptionsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutInterceptor implements HandlerInterceptor {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected final static String STATE_SESSION_VARIABLE = "state";
    protected final static int HTTP_SOCKET_TIMEOUT = 30000;
    protected int httpSocketTimeout = HTTP_SOCKET_TIMEOUT;

    @Autowired(required=false)
    private HttpClient httpClient;

    private IssuerService issuerService;
    private ServerConfigurationService serverConfigurationService;
    private ClientConfigurationService clientConfigurationService;
    private AuthRequestOptionsService authRequestOptionsService = new StaticAuthRequestOptionsService();

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession();

        String idTokenValue = null;
        String redirect_uri = null;
        String issuer = null;
        String state = null;
        String logout_uri = null;
        if (auth instanceof OIDCAuthenticationToken) {
            OIDCAuthenticationToken oidc = (OIDCAuthenticationToken) auth;
            idTokenValue = oidc.getIdToken().getParsedString();
            redirect_uri = (String) session.getAttribute("redirect_uri");
            issuer = (String) session.getAttribute("issuer");
            state = (String) session.getAttribute("state");
            logout_uri = issuer + "endsession?id_token_hint=" + idTokenValue + "&post_logout_redirect_uri=" + URLEncoder.encode(redirect_uri, "UTF-8") + "&state=" + state;

            session.setAttribute("logout_uri", logout_uri);
        }
        */

        return true;
    }

    /*
    public boolean preHandlex(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String accessTokenValue = null;
        if (!trustResolver.isAnonymous(auth)) { // skip lookup on anonymous logins
            if (auth instanceof OIDCAuthenticationToken) {
                // if they're logging into this server from a remote OIDC server, pass through their user info
                OIDCAuthenticationToken oidc = (OIDCAuthenticationToken) auth;
                accessTokenValue = oidc.getAccessTokenValue();

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
            }
        }

        return true;
    }
     */

    private static String getStoredSessionString(HttpSession session, String key) {
        Object o = session.getAttribute(key);
        if (o != null && o instanceof String) {
            return o.toString();
        } else {
            return null;
        }
    }

    protected static String getStoredState(HttpSession session) {
        return getStoredSessionString(session, STATE_SESSION_VARIABLE);
    }

    public IssuerService getIssuerService() {
        return issuerService;
    }

    public void setIssuerService(IssuerService issuerService) {
        this.issuerService = issuerService;
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
