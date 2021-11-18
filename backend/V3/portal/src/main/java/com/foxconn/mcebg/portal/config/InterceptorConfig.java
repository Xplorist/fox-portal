package com.foxconn.mcebg.portal.config;

import com.foxconn.mcebg.portal.interceptor.LogoutInterceptor;
import com.foxconn.mcebg.portal.interceptor.UserInfoInterceptor;
import com.foxconn.mcebg.portal.oidc.client.service.AuthRequestOptionsService;
import com.foxconn.mcebg.portal.oidc.client.service.ClientConfigurationService;
import com.foxconn.mcebg.portal.oidc.client.service.IssuerService;
import com.foxconn.mcebg.portal.oidc.client.service.ServerConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
/**
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
 */
    @Autowired
    IssuerService issuerService;
    @Autowired
    ServerConfigurationService serverConfigurationService;
    @Autowired
    ClientConfigurationService clientConfigurationService;
    @Autowired
    AuthRequestOptionsService authRequestOptionsService;

    /**
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/home").setViewName("home");
        //registry.addViewController("/").setViewName("home");
        //registry.addViewController("/hello").setViewName("hello");
        //registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/hello").setViewName("hello");
    }
    */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(168000)
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/META-INF/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(logoutInterceptor()).addPathPatterns("/hello");
        registry.addInterceptor(userInfoInterceptor()).addPathPatterns("/**");// 攔截所有請求
    }

    @Bean
    public UserInfoInterceptor userInfoInterceptor() {
        return new UserInfoInterceptor();
    }

    @Bean
    public LogoutInterceptor logoutInterceptor() {
        LogoutInterceptor interceptor = new LogoutInterceptor();
        interceptor.setIssuerService(issuerService);
        interceptor.setServerConfigurationService(serverConfigurationService);
        interceptor.setClientConfigurationService(clientConfigurationService);
        interceptor.setAuthRequestOptionsService(authRequestOptionsService);

        return interceptor;
    }
}
