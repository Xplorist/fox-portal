package com.foxconn.mcebg.portal.oidc.client;

import com.foxconn.mcebg.portal.oidc.model.OIDCAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OIDCLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        HttpSession session = httpServletRequest.getSession();

        String idTokenValue = null;
        String redirect_uri = null;
        String issuer = null;
        String state = null;
        String logout_uri = null;
        if (authentication instanceof OIDCAuthenticationToken) {
            OIDCAuthenticationToken oidc = (OIDCAuthenticationToken) authentication;
            idTokenValue = oidc.getIdToken().getParsedString();
            redirect_uri = (String) session.getAttribute("redirect_uri");
            issuer = (String) session.getAttribute("issuer");
            state = (String) session.getAttribute("state");
            try {
                logout_uri = issuer + "endsession?id_token_hint=" + idTokenValue + "&post_logout_redirect_uri=" + URLEncoder.encode(redirect_uri, "UTF-8") + "&state=" + state;
                //session.invalidate();
                httpServletResponse.sendRedirect(logout_uri);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
