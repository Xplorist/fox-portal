package com.foxconn.mcebg.portal.interceptor;

import com.foxconn.mcebg.portal.oidc.model.UserInfo;
import com.foxconn.mcebg.portal.util.HttpClientUtil;
import com.foxconn.mcebg.portal.util.VerifyToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取access_token
        String authorization = request.getHeader("Authorization");
        // 如果不是映射到Controller方法直接放行
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 檢查需不需要access_token
        if(method.isAnnotationPresent(VerifyToken.class)) {
            VerifyToken verifyToken = method.getAnnotation(VerifyToken.class);
            if(verifyToken.required()) {
                if(authorization == null || "".equals(authorization)) {
                    throw new RuntimeException("请求头中必须包含Authorization，axios请求参考 headers: {'Authorization': 'Bearer ' + this.access_token}");
                }

                int indexBearer  = authorization.indexOf("Bearer ");
                if (indexBearer != 0) {
                    throw new RuntimeException("请求头中Authorization的值的格式必须为'Bearer ' + this.access_token");
                }
                String header_access_token = authorization.substring("Bearer ".length());
                if (header_access_token == null || "".equals(header_access_token)) {
                    throw new RuntimeException("请求头的Authorization中必须包含access_token，axios请求参考 headers: {'Authorization': 'Bearer ' + this.access_token}");
                }

                HttpSession session = request.getSession();
                String session_access_token = (String) session.getAttribute("access_token");
                if (session_access_token != null && !"".equals(session_access_token) && header_access_token.equals(session_access_token)) {
                    return true;
                } else {
                    UserInfo userInfo = HttpClientUtil.getUserInfo(header_access_token);
                    if (userInfo == null) {
                        throw new RuntimeException("未登录或登录失效，请登录");
                    }

                    session.setAttribute("access_token", header_access_token);
                    session.setAttribute("userInfo", userInfo);
                }

                return true;
            }
        }

        return true;
    }

}
