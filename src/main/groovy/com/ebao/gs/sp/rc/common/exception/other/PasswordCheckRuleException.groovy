package com.ebao.gs.sp.rc.common.exception.other

import org.springframework.security.authentication.InternalAuthenticationServiceException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class PasswordCheckRuleException extends InternalAuthenticationServiceException {

    PasswordCheckRuleException(String message) {
        super(message)
    }

    PasswordCheckRuleException(String message, Throwable exception) {
        super(message, exception)
    }
}
