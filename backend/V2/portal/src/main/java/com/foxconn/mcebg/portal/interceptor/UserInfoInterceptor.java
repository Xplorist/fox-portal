package com.foxconn.mcebg.portal.interceptor;


import com.foxconn.mcebg.portal.oidc.model.OIDCAuthenticationToken;
import com.foxconn.mcebg.portal.oidc.model.UserInfo;
import com.foxconn.mcebg.portal.oidc.service.UserInfoService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;

public class UserInfoInterceptor implements HandlerInterceptor {
    private Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(GrantedAuthority.class, new JsonSerializer<GrantedAuthority>() {
                @Override
                public JsonElement serialize(GrantedAuthority src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.getAuthority());
                }
            })
            .create();

    @Autowired(required = false)
    private UserInfoService userInfoService;

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    // 主要功能：提取用户信息，然后添加到request中
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof Authentication){
            request.setAttribute("userAuthorities", gson.toJson(auth.getAuthorities()));
        }

        if (!trustResolver.isAnonymous(auth)) { // skip lookup on anonymous logins
            if (auth instanceof OIDCAuthenticationToken) {
                // if they're logging into this server from a remote OIDC server, pass through their user info
                OIDCAuthenticationToken oidc = (OIDCAuthenticationToken) auth;
                if (oidc.getUserInfo() != null) {
                    request.setAttribute("userInfo", oidc.getUserInfo());
                    request.setAttribute("userInfoJson", oidc.getUserInfo().toJson());
                } else {
                    request.setAttribute("userInfo", null);
                    request.setAttribute("userInfoJson", "null");
                }
            } else {
                // don't bother checking if we don't have a principal or a userInfoService to work with
                if (auth != null && auth.getName() != null && userInfoService != null) {

                    // try to look up a user based on the principal's name
                    UserInfo user = userInfoService.getByUsername(auth.getName());

                    // if we have one, inject it so views can use it
                    if (user != null) {
                        request.setAttribute("userInfo", user);
                        request.setAttribute("userInfoJson", user.toJson());
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
