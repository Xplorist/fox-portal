package com.foxconn.mcebg.portal.oidc.client.service.impl;

import com.foxconn.mcebg.portal.oidc.client.model.IssuerServiceResponse;
import com.foxconn.mcebg.portal.oidc.client.service.IssuerService;
import com.google.common.base.Strings;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

public class StaticSingleIssuerService implements IssuerService {

    private String issuer;

    /**
     * @return the issuer
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * @param issuer the issuer to set
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Always returns the configured issuer URL
     *
     * //@see org.mitre.openid.connect.client.service.IssuerService#getIssuer(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public IssuerServiceResponse getIssuer(HttpServletRequest request) {
        return new IssuerServiceResponse(getIssuer(), null, null);
    }

    @PostConstruct
    public void afterPropertiesSet() {

        if (Strings.isNullOrEmpty(issuer)) {
            throw new IllegalArgumentException("Issuer must not be null or empty.");
        }

    }
}
