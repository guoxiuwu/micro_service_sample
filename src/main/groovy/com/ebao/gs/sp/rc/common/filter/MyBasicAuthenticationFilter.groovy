package com.ebao.gs.sp.rc.common.filter

import com.ebao.gs.sp.rc.common.basic.AppContext
import com.ebao.gs.sp.rc.common.exception.other.CaptchaCodeCheckException
import com.ebao.gs.sp.rc.common.security.MyStaticParams
import groovy.util.logging.Slf4j
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsUtils

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpServletResponse

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@Slf4j
class MyBasicAuthenticationFilter extends BasicAuthenticationFilter {

    private final String loginUrlPattern = String.format(".*%s.*", MyStaticParams.PATH_REGEX.LOGIN)

    MyBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager)
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        AppContext.instance.loginContext().isLoginRequest = getRequestPath(request)?.matches(loginUrlPattern)
        if (CorsUtils.isPreFlightRequest(request)) {
            response.setHeader('Access-Control-Allow-Origin', '*')
            response.setHeader('Access-Control-Allow-Headers',
                    request.getHeader('Access-Control-Request-Headers'))
            return
        }
        if (AppContext.instance.loginContext().isLoginRequest) {
            // Captcha validation
            String generatedCaptchaCode = request.getSession().getAttribute(MyStaticParams.HTTP_REQUEST_PARAM.CAPTCHA_CODE_KEY)
            if (generatedCaptchaCode && generatedCaptchaCode != request.getHeader(MyStaticParams.HTTP_REQUEST_PARAM.CAPTCHA_CODE_KEY)) {
                authenticationEntryPoint.commence(request, response, new CaptchaCodeCheckException("Failed to Verify captcha code"))
                return
            }
        }
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request) {
            @Override
            public String getHeader(String name) {
                if (name == MyStaticParams.HTTP_REQUEST_PARAM.AUTHORIZATION) {
                    final String accessToken = request.getParameter(MyStaticParams.HTTP_REQUEST_PARAM.ACCESS_TOKEN)
                    if (accessToken) {
                        return accessToken
                    }
                }
                return super.getHeader(name)
            }
        }
        super.doFilterInternal(wrapper, response, chain)
    }

    private String getRequestPath(HttpServletRequest request) {
        String url = request.getServletPath()
        if (request.getPathInfo() != null) {
            url = url + request.getPathInfo()
        }
        return url.toLowerCase()
    }
}