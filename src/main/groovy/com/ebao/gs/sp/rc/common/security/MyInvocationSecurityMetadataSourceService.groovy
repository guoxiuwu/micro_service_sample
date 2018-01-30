package com.ebao.gs.sp.rc.common.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Component

/**
 * Created by xiuwu.guo on 11/7/2017.
 */
@Component
class MyInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    MyAPIAuthorityManager apiAuthorityManager

    @Override
    Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object
        def matchedURLNeedAuths = []
        apiAuthorityManager.getAPIAndRolesMapping()?.each { k, v ->
            RequestMatcher requestMatcher = new AntPathRequestMatcher(k)
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                matchedURLNeedAuths = apiAuthorityManager.getAPIAndRolesMapping().get(k)
            }
        }
        return matchedURLNeedAuths
    }

    @Override
    Collection<ConfigAttribute> getAllConfigAttributes() {
        apiAuthorityManager.getAPIAndRolesMapping().collect { key, value -> value }.flatten()
    }

    @Override
    boolean supports(Class<?> clazz) {
        return true
    }
}
