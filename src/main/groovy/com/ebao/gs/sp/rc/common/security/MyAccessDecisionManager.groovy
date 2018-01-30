package com.ebao.gs.sp.rc.common.security

import org.apache.commons.collections.CollectionUtils
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.SecurityConfig
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component

/**
 * Created by xiuwu.guo on 11/7/2017.
 */
class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configApiAndAuths) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configApiAndAuths)) {
            return
        }
        // need match one role only
        boolean matched = true
        configApiAndAuths?.each { SecurityConfig securityConfig ->
            String needAuth = securityConfig.getAttribute()
            authentication.getAuthorities()?.each { GrantedAuthority ga ->
                if (needAuth.trim().equalsIgnoreCase(ga.getAuthority().trim())) {
                    matched = true
                    return
                }
            }
        }
        if (!matched) {
            throw new AccessDeniedException("insufficient privileges")
        }
    }

    @Override
    boolean supports(ConfigAttribute attribute) {
        return true
    }

    @Override
    boolean supports(Class<?> clazz) {
        return true
    }
}
