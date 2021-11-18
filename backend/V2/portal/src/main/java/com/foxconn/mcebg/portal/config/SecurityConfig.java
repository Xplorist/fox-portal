package com.foxconn.mcebg.portal.config;

import com.foxconn.mcebg.portal.oidc.client.*;
import com.foxconn.mcebg.portal.oidc.client.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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

    @Autowired
    IssuerService issuerService;
    @Autowired
    ServerConfigurationService serverConfigurationService;
    @Autowired
    ClientConfigurationService clientConfigurationService;
    @Autowired
    AuthRequestOptionsService authRequestOptionsService;
    @Autowired
    AuthRequestUrlBuilder authRequestUrlBuilder;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(oidcAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        //http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());
        /*
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 登录URL
                .antMatchers("/login").permitAll()
                // swagger
                .antMatchers("/swagger**//*/*").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/doc.html").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();

         //http.antMatcher("/**").httpBasic().authenticationEntryPoint(authenticationEntryPoint());

         //.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());

         //http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        */

        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .cors().and().csrf().disable()
                .authorizeRequests()
                // 登录URL
                //.antMatchers("/login").permitAll()
                // swagger
                .antMatchers("/swagger**/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/doc.html").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();

        //http.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/").logoutSuccessHandler(oidcLogoutSuccessHandler());
        //http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        //http.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/").logoutSuccessHandler(oidcLogoutSuccessHandler());
        http.logout().logoutUrl("/logout").addLogoutHandler(new OIDCLogoutHandler()).invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/");

        http.addFilterBefore(oidcAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public OIDCLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OIDCLogoutSuccessHandler handler = new OIDCLogoutSuccessHandler();
        handler.setIssuerService(issuerService);
        handler.setServerConfigurationService(serverConfigurationService);
        handler.setClientConfigurationService(clientConfigurationService);
        handler.setAuthRequestOptionsService(authRequestOptionsService);

        return handler;
    }

    @Bean
    public OIDCAuthenticationProvider oidcAuthenticationProvider() {
        OIDCAuthenticationProvider provider = new OIDCAuthenticationProvider();
        NamedAdminAuthoritiesMapper authoritiesMapper = new NamedAdminAuthoritiesMapper();
        authoritiesMapper.setAdmins(namedAdmins());

        provider.setAuthoritiesMapper(authoritiesMapper);

        return provider;
    }

    @Bean
    public Set<SubjectIssuerGrantedAuthority> namedAdmins() {
        Set<SubjectIssuerGrantedAuthority> set = new HashSet<>();
        SubjectIssuerGrantedAuthority authority = new SubjectIssuerGrantedAuthority(subject, serverUrl);

        set.add(authority);

        return set;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        LoginUrlAuthenticationEntryPoint entryPoint =
                new LoginUrlAuthenticationEntryPoint("/openid_connect_login");
        return entryPoint;
    }

    @Bean
    public OIDCAuthenticationFilter oidcAuthenticationFilter() throws Exception {
        OIDCAuthenticationFilter filter = new OIDCAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setIssuerService(issuerService);
        filter.setServerConfigurationService(serverConfigurationService);
        filter.setClientConfigurationService(clientConfigurationService);
        filter.setAuthRequestOptionsService(authRequestOptionsService);
        filter.setAuthRequestUrlBuilder(authRequestUrlBuilder);

        return filter;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        //配置模板
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        // 使用HTML的模式，也就是支持HTML5的方式，使用data-th-*的H5写法来写thymeleaf的标签语法
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // 之前在application.properties中看到的缓存配置
        templateResolver.setCacheable(true);

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        //模板引擎增加SpringSecurityDialect，让模板能用到sec前缀，获取spring security的内容
        SpringTemplateEngine engine = new SpringTemplateEngine();
        SpringSecurityDialect securityDialect = new SpringSecurityDialect();
        Set<IDialect> dialects = new HashSet<>();
        dialects.add(securityDialect);
        engine.setAdditionalDialects(dialects);

        engine.setTemplateResolver(templateResolver());
        //允许在内容中使用spring EL表达式
        engine.setEnableSpringELCompiler(true);

        return engine;
    }

    //声明ViewResolver
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }
}
