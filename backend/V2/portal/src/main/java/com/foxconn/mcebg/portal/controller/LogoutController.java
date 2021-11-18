package com.foxconn.mcebg.portal.controller;

import com.foxconn.mcebg.portal.oidc.model.OIDCAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class LogoutController {
    //@RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
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

        session.invalidate();

        if (logout_uri != null) {
            return "redirect:" + logout_uri;
        } else {
            return "index";
        }
    }
}
