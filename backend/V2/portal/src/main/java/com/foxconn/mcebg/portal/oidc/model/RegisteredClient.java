/*******************************************************************************
 * Copyright 2018 The MIT Internet Trust Consortium
 *
 * Portions copyright 2011-2013 The MITRE Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 *
 */
package com.foxconn.mcebg.portal.oidc.model;

import com.google.gson.JsonObject;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWT;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author jricher
 *
 */
public class RegisteredClient {

	// these fields are needed in addition to the ones in ClientDetailsEntity
	private String registrationAccessToken;
	private String registrationClientUri;
	private Date clientSecretExpiresAt;
	private Date clientIdIssuedAt;
	private ClientDetailsEntity client;
	private JsonObject src;

	/**
	 *
	 */
	public RegisteredClient() {
		this.client = new ClientDetailsEntity();
	}

	/**
	 * @param client
	 */
	public RegisteredClient(ClientDetailsEntity client) {
		this.client = client;
	}

	/**
	 * @param client
	 * @param registrationAccessToken
	 * @param registrationClientUri
	 */
	public RegisteredClient(ClientDetailsEntity client, String registrationAccessToken, String registrationClientUri) {
		this.client = client;
		this.registrationAccessToken = registrationAccessToken;
		this.registrationClientUri = registrationClientUri;
	}

	/**
	 * @return the client
	 */
	public ClientDetailsEntity getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(ClientDetailsEntity client) {
		this.client = client;
	}
	
	public String getClientDescription() {
		return client.getClientDescription();
	}
	
	public void setClientDescription(String clientDescription) {
		client.setClientDescription(clientDescription);
	}
	
	public boolean isAllowRefresh() {
		return client.isAllowRefresh();
	}
	
	public boolean isReuseRefreshToken() {
		return client.isReuseRefreshToken();
	}
	
	public void setReuseRefreshToken(boolean reuseRefreshToken) {
		client.setReuseRefreshToken(reuseRefreshToken);
	}
	
	public Integer getIdTokenValiditySeconds() {
		return client.getIdTokenValiditySeconds();
	}
	
	public void setIdTokenValiditySeconds(Integer idTokenValiditySeconds) {
		client.setIdTokenValiditySeconds(idTokenValiditySeconds);
	}
	
	public boolean isDynamicallyRegistered() {
		return client.isDynamicallyRegistered();
	}
	
	public void setDynamicallyRegistered(boolean dynamicallyRegistered) {
		client.setDynamicallyRegistered(dynamicallyRegistered);
	}
	
	public boolean isAllowIntrospection() {
		return client.isAllowIntrospection();
	}
	
	public void setAllowIntrospection(boolean allowIntrospection) {
		client.setAllowIntrospection(allowIntrospection);
	}
	
	public boolean isSecretRequired() {
		return client.isSecretRequired();
	}
	
	public boolean isScoped() {
		return client.isScoped();
	}
	
	public String getClientId() {
		return client.getClientId();
	}
	
	public void setClientId(String clientId) {
		client.setClientId(clientId);
	}
	
	public String getClientSecret() {
		return client.getClientSecret();
	}
	
	public void setClientSecret(String clientSecret) {
		client.setClientSecret(clientSecret);
	}
	
	public Set<String> getScope() {
		return client.getScope();
	}
	
	public void setScope(Set<String> scope) {
		client.setScope(scope);
	}
	
	public Set<String> getGrantTypes() {
		return client.getGrantTypes();
	}
	
	public void setGrantTypes(Set<String> grantTypes) {
		client.setGrantTypes(grantTypes);
	}
	
	public Set<String> getAuthorizedGrantTypes() {
		return client.getAuthorizedGrantTypes();
	}
	
	public Set<GrantedAuthority> getAuthorities() {
		return client.getAuthorities();
	}
	
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		client.setAuthorities(authorities);
	}
	
	public Integer getAccessTokenValiditySeconds() {
		return client.getAccessTokenValiditySeconds();
	}
	
	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		client.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
	}
	
	public Integer getRefreshTokenValiditySeconds() {
		return client.getRefreshTokenValiditySeconds();
	}
	
	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		client.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
	}
	
	public Set<String> getRedirectUris() {
		return client.getRedirectUris();
	}
	
	public void setRedirectUris(Set<String> redirectUris) {
		client.setRedirectUris(redirectUris);
	}
	
	public Set<String> getRegisteredRedirectUri() {
		return client.getRegisteredRedirectUri();
	}
	
	public Set<String> getResourceIds() {
		return client.getResourceIds();
	}
	
	public void setResourceIds(Set<String> resourceIds) {
		client.setResourceIds(resourceIds);
	}
	
	public Map<String, Object> getAdditionalInformation() {
		return client.getAdditionalInformation();
	}
	
	public ClientDetailsEntity.AppType getApplicationType() {
		return client.getApplicationType();
	}
	
	public void setApplicationType(ClientDetailsEntity.AppType applicationType) {
		client.setApplicationType(applicationType);
	}
	
	public String getClientName() {
		return client.getClientName();
	}
	
	public void setClientName(String clientName) {
		client.setClientName(clientName);
	}
	
	public ClientDetailsEntity.AuthMethod getTokenEndpointAuthMethod() {
		return client.getTokenEndpointAuthMethod();
	}
	
	public void setTokenEndpointAuthMethod(ClientDetailsEntity.AuthMethod tokenEndpointAuthMethod) {
		client.setTokenEndpointAuthMethod(tokenEndpointAuthMethod);
	}
	
	public ClientDetailsEntity.SubjectType getSubjectType() {
		return client.getSubjectType();
	}
	
	public void setSubjectType(ClientDetailsEntity.SubjectType subjectType) {
		client.setSubjectType(subjectType);
	}
	
	public Set<String> getContacts() {
		return client.getContacts();
	}
	
	public void setContacts(Set<String> contacts) {
		client.setContacts(contacts);
	}
	
	public String getLogoUri() {
		return client.getLogoUri();
	}
	
	public void setLogoUri(String logoUri) {
		client.setLogoUri(logoUri);
	}
	
	public String getPolicyUri() {
		return client.getPolicyUri();
	}
	
	public void setPolicyUri(String policyUri) {
		client.setPolicyUri(policyUri);
	}
	
	public String getClientUri() {
		return client.getClientUri();
	}
	
	public void setClientUri(String clientUri) {
		client.setClientUri(clientUri);
	}
	
	public String getTosUri() {
		return client.getTosUri();
	}
	
	public void setTosUri(String tosUri) {
		client.setTosUri(tosUri);
	}
	
	public String getJwksUri() {
		return client.getJwksUri();
	}
	
	public void setJwksUri(String jwksUri) {
		client.setJwksUri(jwksUri);
	}
	
	public JWKSet getJwks() {
		return client.getJwks();
	}

	
	public void setJwks(JWKSet jwks) {
		client.setJwks(jwks);
	}

	
	public String getSectorIdentifierUri() {
		return client.getSectorIdentifierUri();
	}
	
	public void setSectorIdentifierUri(String sectorIdentifierUri) {
		client.setSectorIdentifierUri(sectorIdentifierUri);
	}
	
	public Integer getDefaultMaxAge() {
		return client.getDefaultMaxAge();
	}
	/**
	 * @param defaultMaxAge
	 * ////@see org.mitre.oauth2.model.ClientDetailsEntity#setDefaultMaxAge(Integer)
	 */
	public void setDefaultMaxAge(Integer defaultMaxAge) {
		client.setDefaultMaxAge(defaultMaxAge);
	}
	/**
	 * @return
	 * ////@see org.mitre.oauth2.model.ClientDetailsEntity#getRequireAuthTime()
	 */
	public Boolean getRequireAuthTime() {
		return client.getRequireAuthTime();
	}
	/**
	 * @param requireAuthTime
	 * ////@see org.mitre.oauth2.model.ClientDetailsEntity#setRequireAuthTime(Boolean)
	 */
	public void setRequireAuthTime(Boolean requireAuthTime) {
		client.setRequireAuthTime(requireAuthTime);
	}
	/**
	 * @return
	 * ////@see org.mitre.oauth2.model.ClientDetailsEntity#getResponseTypes()
	 */
	public Set<String> getResponseTypes() {
		return client.getResponseTypes();
	}
	/**
	 * @param responseTypes
	 * ////@see org.mitre.oauth2.model.ClientDetailsEntity#setResponseTypes(Set)
	 */
	public void setResponseTypes(Set<String> responseTypes) {
		client.setResponseTypes(responseTypes);
	}
	/**
	 * @return
	 * ////@see org.mitre.oauth2.model.ClientDetailsEntity#getDefaultACRvalues()
	 */
	public Set<String> getDefaultACRvalues() {
		return client.getDefaultACRvalues();
	}
	/**
	 * @param defaultACRvalues
	 * ////@see org.mitre.oauth2.model.ClientDetailsEntity#setDefaultACRvalues(Set)
	 */
	public void setDefaultACRvalues(Set<String> defaultACRvalues) {
		client.setDefaultACRvalues(defaultACRvalues);
	}
	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getInitiateLoginUri()
	 */
	public String getInitiateLoginUri() {
		return client.getInitiateLoginUri();
	}
	/**
	 * @param initiateLoginUri
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setInitiateLoginUri(String)
	 */
	public void setInitiateLoginUri(String initiateLoginUri) {
		client.setInitiateLoginUri(initiateLoginUri);
	}
	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getPostLogoutRedirectUris()
	 */
	public Set<String> getPostLogoutRedirectUris() {
		return client.getPostLogoutRedirectUris();
	}
	/**
	 * @param postLogoutRedirectUri
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setPostLogoutRedirectUris(String)
	 */
	public void setPostLogoutRedirectUris(Set<String> postLogoutRedirectUri) {
		client.setPostLogoutRedirectUris(postLogoutRedirectUri);
	}
	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getRequestUris()
	 */
	public Set<String> getRequestUris() {
		return client.getRequestUris();
	}
	/**
	 * @param requestUris
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setRequestUris(Set)
	 */
	public void setRequestUris(Set<String> requestUris) {
		client.setRequestUris(requestUris);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getRequestObjectSigningAlg()
	 */
	public JWSAlgorithm getRequestObjectSigningAlg() {
		return client.getRequestObjectSigningAlg();
	}

	/**
	 * @param requestObjectSigningAlg
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setRequestObjectSigningAlg(JWSAlgorithm)
	 */
	public void setRequestObjectSigningAlg(JWSAlgorithm requestObjectSigningAlg) {
		client.setRequestObjectSigningAlg(requestObjectSigningAlg);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getUserInfoSignedResponseAlg()
	 */
	public JWSAlgorithm getUserInfoSignedResponseAlg() {
		return client.getUserInfoSignedResponseAlg();
	}

	/**
	 * @param userInfoSignedResponseAlg
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setUserInfoSignedResponseAlg(JWSAlgorithm)
	 */
	public void setUserInfoSignedResponseAlg(JWSAlgorithm userInfoSignedResponseAlg) {
		client.setUserInfoSignedResponseAlg(userInfoSignedResponseAlg);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getUserInfoEncryptedResponseAlg()
	 */
	public JWEAlgorithm getUserInfoEncryptedResponseAlg() {
		return client.getUserInfoEncryptedResponseAlg();
	}

	/**
	 * @param userInfoEncryptedResponseAlg
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setUserInfoEncryptedResponseAlg(JWEAlgorithm)
	 */
	public void setUserInfoEncryptedResponseAlg(JWEAlgorithm userInfoEncryptedResponseAlg) {
		client.setUserInfoEncryptedResponseAlg(userInfoEncryptedResponseAlg);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getUserInfoEncryptedResponseEnc()
	 */
	public EncryptionMethod getUserInfoEncryptedResponseEnc() {
		return client.getUserInfoEncryptedResponseEnc();
	}

	/**
	 * @param userInfoEncryptedResponseEnc
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setUserInfoEncryptedResponseEnc(EncryptionMethod)
	 */
	public void setUserInfoEncryptedResponseEnc(EncryptionMethod userInfoEncryptedResponseEnc) {
		client.setUserInfoEncryptedResponseEnc(userInfoEncryptedResponseEnc);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getIdTokenSignedResponseAlg()
	 */
	public JWSAlgorithm getIdTokenSignedResponseAlg() {
		return client.getIdTokenSignedResponseAlg();
	}

	/**
	 * @param idTokenSignedResponseAlg
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setIdTokenSignedResponseAlg(JWSAlgorithm)
	 */
	public void setIdTokenSignedResponseAlg(JWSAlgorithm idTokenSignedResponseAlg) {
		client.setIdTokenSignedResponseAlg(idTokenSignedResponseAlg);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getIdTokenEncryptedResponseAlg()
	 */
	public JWEAlgorithm getIdTokenEncryptedResponseAlg() {
		return client.getIdTokenEncryptedResponseAlg();
	}

	/**
	 * @param idTokenEncryptedResponseAlg
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setIdTokenEncryptedResponseAlg(JWEAlgorithm)
	 */
	public void setIdTokenEncryptedResponseAlg(JWEAlgorithm idTokenEncryptedResponseAlg) {
		client.setIdTokenEncryptedResponseAlg(idTokenEncryptedResponseAlg);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getIdTokenEncryptedResponseEnc()
	 */
	public EncryptionMethod getIdTokenEncryptedResponseEnc() {
		return client.getIdTokenEncryptedResponseEnc();
	}

	/**
	 * @param idTokenEncryptedResponseEnc
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setIdTokenEncryptedResponseEnc(EncryptionMethod)
	 */
	public void setIdTokenEncryptedResponseEnc(EncryptionMethod idTokenEncryptedResponseEnc) {
		client.setIdTokenEncryptedResponseEnc(idTokenEncryptedResponseEnc);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getTokenEndpointAuthSigningAlg()
	 */
	public JWSAlgorithm getTokenEndpointAuthSigningAlg() {
		return client.getTokenEndpointAuthSigningAlg();
	}

	/**
	 * @param tokenEndpointAuthSigningAlg
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setTokenEndpointAuthSigningAlg(JWSAlgorithm)
	 */
	public void setTokenEndpointAuthSigningAlg(JWSAlgorithm tokenEndpointAuthSigningAlg) {
		client.setTokenEndpointAuthSigningAlg(tokenEndpointAuthSigningAlg);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getCreatedAt()
	 */
	public Date getCreatedAt() {
		return client.getCreatedAt();
	}
	/**
	 * @param createdAt
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setCreatedAt(Date)
	 */
	public void setCreatedAt(Date createdAt) {
		client.setCreatedAt(createdAt);
	}
	/**
	 * @return the registrationAccessToken
	 */
	public String getRegistrationAccessToken() {
		return registrationAccessToken;
	}
	/**
	 * @param registrationAccessToken the registrationAccessToken to set
	 */
	public void setRegistrationAccessToken(String registrationAccessToken) {
		this.registrationAccessToken = registrationAccessToken;
	}
	/**
	 * @return the registrationClientUri
	 */
	public String getRegistrationClientUri() {
		return registrationClientUri;
	}
	/**
	 * @param registrationClientUri the registrationClientUri to set
	 */
	public void setRegistrationClientUri(String registrationClientUri) {
		this.registrationClientUri = registrationClientUri;
	}
	/**
	 * @return the clientSecretExpiresAt
	 */
	public Date getClientSecretExpiresAt() {
		return clientSecretExpiresAt;
	}
	/**
	 * //@param clientSecretExpiresAt the clientSecretExpiresAt to set
	 */
	public void setClientSecretExpiresAt(Date expiresAt) {
		this.clientSecretExpiresAt = expiresAt;
	}
	/**
	 * @return the clientIdIssuedAt
	 */
	public Date getClientIdIssuedAt() {
		return clientIdIssuedAt;
	}
	/**
	 * //@param clientIdIssuedAt the clientIdIssuedAt to set
	 */
	public void setClientIdIssuedAt(Date issuedAt) {
		this.clientIdIssuedAt = issuedAt;
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getClaimsRedirectUris()
	 */
	public Set<String> getClaimsRedirectUris() {
		return client.getClaimsRedirectUris();
	}

	/**
	 * @param claimsRedirectUris
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setClaimsRedirectUris(Set)
	 */
	public void setClaimsRedirectUris(Set<String> claimsRedirectUris) {
		client.setClaimsRedirectUris(claimsRedirectUris);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getSoftwareStatement()
	 */
	public JWT getSoftwareStatement() {
		return client.getSoftwareStatement();
	}

	/**
	 * @param softwareStatement
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setSoftwareStatement(JWT)
	 */
	public void setSoftwareStatement(JWT softwareStatement) {
		client.setSoftwareStatement(softwareStatement);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getCodeChallengeMethod()
	 */
	public PKCEAlgorithm getCodeChallengeMethod() {
		return client.getCodeChallengeMethod();
	}

	/**
	 * @param codeChallengeMethod
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setCodeChallengeMethod(org.mitre.oauth2.model.PKCEAlgorithm)
	 */
	public void setCodeChallengeMethod(PKCEAlgorithm codeChallengeMethod) {
		client.setCodeChallengeMethod(codeChallengeMethod);
	}

	/**
	 * @return the src
	 */
	public JsonObject getSource() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSource(JsonObject src) {
		this.src = src;
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getDeviceCodeValiditySeconds()
	 */
	public Integer getDeviceCodeValiditySeconds() {
		return client.getDeviceCodeValiditySeconds();
	}

	/**
	 * @param deviceCodeValiditySeconds
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setDeviceCodeValiditySeconds(Integer)
	 */
	public void setDeviceCodeValiditySeconds(Integer deviceCodeValiditySeconds) {
		client.setDeviceCodeValiditySeconds(deviceCodeValiditySeconds);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getSoftwareId()
	 */
	public String getSoftwareId() {
		return client.getSoftwareId();
	}

	/**
	 * @param softwareId
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setSoftwareId(String)
	 */
	public void setSoftwareId(String softwareId) {
		client.setSoftwareId(softwareId);
	}

	/**
	 * @return
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#getSoftwareVersion()
	 */
	public String getSoftwareVersion() {
		return client.getSoftwareVersion();
	}

	/**
	 * @param softwareVersion
	 * //@see org.mitre.oauth2.model.ClientDetailsEntity#setSoftwareVersion(String)
	 */
	public void setSoftwareVersion(String softwareVersion) {
		client.setSoftwareVersion(softwareVersion);
	}



}
