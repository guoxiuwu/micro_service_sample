package com.ebao.gs.sp.rc.common.security

import com.ebao.gs.sp.rc.common.basic.AppContext
import com.ebao.gs.sp.rc.model.User
import com.ebao.gs.sp.rc.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by xiuwu.guo on 11/7/2017.
 */
@Component
class MyLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    UserService userService

    @Override
    void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User localUser = (User) authentication.getPrincipal()
        //after user login success clean related info for check user whether locked
        userService.unlockUserIfNecessary(localUser.username)

        String userLang = ((HttpServletRequest) request).getHeader(MyStaticParams.HTTP_REQUEST_PARAM.LANG)
        localUser.userProfile.preferredLang = userLang ? userLang : localUser.userProfile.preferredLang
        AppContext.instance.loginContext().currentUser = localUser
        AppContext.instance.loginContext().fromIp = getIpAddress(request)
        super.onAuthenticationSuccess(request, response, authentication)
    }

    private final String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for")
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP")
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP")
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP")
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR")
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr()
        }
        return ip
    }
}
