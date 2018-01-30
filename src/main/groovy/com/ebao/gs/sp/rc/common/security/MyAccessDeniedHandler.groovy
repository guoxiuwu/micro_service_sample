package com.ebao.gs.sp.rc.common.security

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandlerImpl

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by xiuwu.guo on 11/7/2017.
 */
class MyAccessDeniedHandler extends AccessDeniedHandlerImpl {

    @Override
    void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader('Access-Control-Allow-Origin', '*')
        super.handle(request, response, accessDeniedException)
    }
}

