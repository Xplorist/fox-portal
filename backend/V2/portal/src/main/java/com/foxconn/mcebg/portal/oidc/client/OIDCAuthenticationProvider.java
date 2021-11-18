package com.foxconn.mcebg.portal.oidc.client;

import com.foxconn.mcebg.portal.oidc.model.OIDCAuthenticationToken;
import com.foxconn.mcebg.portal.oidc.model.PendingOIDCAuthenticationToken;
import com.foxconn.mcebg.portal.oidc.model.UserInfo;
import com.google.common.base.Strings;
import com.nimbusds.jwt.JWT;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

public class OIDCAuthenticationProvider implements AuthenticationProvider {
    private UserInfoFetcher userInfoFetcher = new UserInfoFetcher();
    private OIDCAuthoritiesMapper authoritiesMapper = new NamedAdminAuthoritiesMapper();

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        if (!supports(authentication.getClass())) {
            return null;
        }

        if (authentication instanceof PendingOIDCAuthenticationToken) {

            PendingOIDCAuthenticationToken token = (PendingOIDCAuthenticationToken) authentication;

            // get the ID Token value out
            JWT idToken = token.getIdToken();

            // load the user info if we can
            UserInfo userInfo = userInfoFetcher.loadUserInfo(token);

            if (userInfo == null) {
                // user info not found -- could be an error, could be fine
            } else {
                // if we found userinfo, double check it
                if (!Strings.isNullOrEmpty(userInfo.getSub()) && !userInfo.getSub().equals(token.getSub())) {
                    // the userinfo came back and the user_id fields don't match what was in the id_token
                    throw new UsernameNotFoundException("user_id mismatch between id_token and user_info call: " + token.getSub() + " / " + userInfo.getSub());
                }
            }

            return createAuthenticationToken(token, authoritiesMapper.mapAuthorities(idToken, userInfo), userInfo);
        }

        return null;
    }

    protected Authentication createAuthenticationToken(PendingOIDCAuthenticationToken token, Collection<? extends GrantedAuthority> authorities, UserInfo userInfo) {
        return new OIDCAuthenticationToken(token.getSub(),
                token.getIssuer(),
                userInfo, authorities,
                token.getIdToken(), token.getAccessTokenValue(), token.getRefreshTokenValue());
    }

    /**
     * @param userInfoFetcher
     */
    public void setUserInfoFetcher(UserInfoFetcher userInfoFetcher) {
        this.userInfoFetcher = userInfoFetcher;
    }

    /**
     * @param authoritiesMapper
     */
    public void setAuthoritiesMapper(OIDCAuthoritiesMapper authoritiesMapper) {
        this.authoritiesMapper = authoritiesMapper;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PendingOIDCAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
