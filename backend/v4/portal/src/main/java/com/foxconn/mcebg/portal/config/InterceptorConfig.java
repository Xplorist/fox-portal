package com.foxconn.mcebg.portal.config;

import com.foxconn.mcebg.portal.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");// 攔截所有請求
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
