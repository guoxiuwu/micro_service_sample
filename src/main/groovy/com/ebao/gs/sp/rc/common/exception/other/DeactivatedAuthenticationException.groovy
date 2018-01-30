package com.ebao.gs.sp.rc.common.exception.other

import org.springframework.security.authentication.InternalAuthenticationServiceException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class DeactivatedAuthenticationException extends InternalAuthenticationServiceException {

    DeactivatedAuthenticationException(String msg) {
        super(msg)
    }

    DeactivatedAuthenticationException(message, exception) {
        super(message, exception);
    }
}