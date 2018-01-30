package com.ebao.gs.sp.rc.common.filter

import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.SecurityMetadataSource
import org.springframework.security.access.intercept.AbstractSecurityInterceptor
import org.springframework.security.access.intercept.InterceptorStatusToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource

import javax.servlet.*

/**
 * Created by xiuwu.guo on 11/7/2017.
 */
class MySecurityFilter extends AbstractSecurityInterceptor
        implements Filter {

    private FilterInvocationSecurityMetadataSource mySecurityMetadataSource

    MySecurityFilter(FilterInvocationSecurityMetadataSource metadataSource, AccessDecisionManager decisionManager,
                     AuthenticationManager authenticationManager) {
        super.setAccessDecisionManager(decisionManager)
        super.setAuthenticationManager(authenticationManager)
        this.mySecurityMetadataSource = metadataSource
    }

    @Override
    Class<?> getSecureObjectClass() {
        return FilterInvocation.class
    }

    @Override
    SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.mySecurityMetadataSource
    }


    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain)
        InterceptorStatusToken token = super.beforeInvocation(fi)
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse())
        } finally {
            super.afterInvocation(token, null)
        }
    }

    @Override
    void destroy() {}

    @Override
    void init(FilterConfig filterConfig) throws ServletException {}
}
