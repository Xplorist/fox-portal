package com.foxconn.mcebg.portal.oidc.client.service;

import com.foxconn.mcebg.portal.oidc.client.model.IssuerServiceResponse;

import javax.servlet.http.HttpServletRequest;

public interface IssuerService {
    IssuerServiceResponse getIssuer(HttpServletRequest request);
}
