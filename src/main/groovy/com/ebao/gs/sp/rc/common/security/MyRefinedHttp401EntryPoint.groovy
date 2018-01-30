package com.ebao.gs.sp.rc.common.security

import com.ebao.gs.sp.rc.common.basic.ConfigVariable
import com.ebao.gs.sp.rc.common.exception.other.CaptchaCodeCheckException
import com.ebao.gs.sp.rc.common.exception.other.DeactivatedAuthenticationException
import com.ebao.gs.sp.rc.common.exception.other.PasswordCheckRuleException
import com.ebao.gs.sp.rc.common.exception.ErrorCode
import com.ebao.gs.sp.rc.common.utils.Resp
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.*
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@Slf4j
@Component
class MyRefinedHttp401EntryPoint extends BasicAuthenticationEntryPoint {

    @Autowired
    ConfigVariable configVariable

    @Override
    void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.debug("Pre-authenticated entry point called. Rejecting access")
        def responseCode = ErrorCode.ACCOUNT_INCORRECT_ERROR
        def errorParams
        if (authException instanceof LockedException) {
            responseCode = ErrorCode.ACCOUNT_LOCKED_ERROR
            Integer lockTimes = configVariable.login_failed_locked_minutes
            errorParams = [lockTimes?.toString()]
        } else if (authException instanceof BadCredentialsException) {
            responseCode = ErrorCode.ACCOUNT_INCORRECT_ERROR
        } else if (authException instanceof AccountExpiredException) {
            responseCode = ErrorCode.ACCOUNT_EXPIRED_ERROR
        } else if (authException.cause instanceof DeactivatedAuthenticationException) {
            responseCode = ErrorCode.ACCOUNT_TENANT_DISABLED_ERROR
        } else if (authException instanceof PasswordCheckRuleException) {
            responseCode = ErrorCode.ACCOUNT_PASSWORD_BREAKING_RULE_ERROR
        } else if (authException instanceof DisabledException) {
            responseCode = ErrorCode.ACCOUNT_DISABLED_ERROR
        } else if (authException instanceof CredentialsExpiredException) {
            responseCode = ErrorCode.ACCOUNT_CREDENTIAL_EXPIRED_ERROR
        } else if (authException instanceof CaptchaCodeCheckException) {
            responseCode = ErrorCode.ACCOUNT_CAPTCHA_CODE_VERIFICATION_FAILED
        }
        response.setHeader('Access-Control-Allow-Origin', '*')
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(Resp.failWithPlainMessage(responseCode, authException.message, errorParams).toString())
        response.getWriter().flush()
    }

    @Override
    public void afterPropertiesSet() throws Exception {}
}

