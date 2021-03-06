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
        // ?????? csrf, ??????????????????JWT????????????????????????csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // ??????????????????
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // ??????URL
                .antMatchers("/login").permitAll()
                // swagger
                .antMatchers("/swagger**//*/*").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/doc.html").permitAll()
                // ????????????????????????????????????
                .anyRequest().authenticated();

         //http.antMatcher("/**").httpBasic().authenticationEntryPoint(authenticationEntryPoint());

         //.and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());

         //http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        */

        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .cors().and().csrf().disable()
                .authorizeRequests()
                // ??????URL
                //.antMatchers("/login").permitAll()
                // swagger
                .antMatchers("/swagger**/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/doc.html").permitAll()
                // ????????????????????????????????????
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
        //????????????
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        // ??????HTML???????????????????????????HTML5??????????????????data-th-*???H5????????????thymeleaf???????????????
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // ?????????application.properties????????????????????????
        templateResolver.setCacheable(true);

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        //??????????????????SpringSecurityDialect?????????????????????sec???????????????spring security?????????
        SpringTemplateEngine engine = new SpringTemplateEngine();
        SpringSecurityDialect securityDialect = new SpringSecurityDialect();
        Set<IDialect> dialects = new HashSet<>();
        dialects.add(securityDialect);
        engine.setAdditionalDialects(dialects);

        engine.setTemplateResolver(templateResolver());
        //????????????????????????spring EL?????????
        engine.setEnableSpringELCompiler(true);

        return engine;
    }

    //??????ViewResolver
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }
}
