package com.ebao.gs.sp.rc.common.exception.other

import org.springframework.security.authentication.InternalAuthenticationServiceException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class CaptchaCodeCheckException extends InternalAuthenticationServiceException {

    CaptchaCodeCheckException() {
        super()
    }

    CaptchaCodeCheckException(String message) {
        super(message)
    }

    CaptchaCodeCheckException(String message, Throwable exception) {
        super(message, exception)
    }

}
