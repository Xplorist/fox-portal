/*******************************************************************************
 * Copyright 2018 The MIT Internet Trust Consortium
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

package com.foxconn.mcebg.portal.oidc.client;

import com.foxconn.mcebg.portal.oidc.model.UserInfo;
import com.nimbusds.jwt.JWT;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author jricher
 *
 */
public interface OIDCAuthoritiesMapper {

	/**
	 * @param idToken the ID Token (parsed as a JWT, cannot be @null)
	 * @param userInfo userInfo of the current user (could be @null)
	 * @return the set of authorities to map to this user
	 */
	Collection<? extends GrantedAuthority> mapAuthorities(JWT idToken, UserInfo userInfo);

}
