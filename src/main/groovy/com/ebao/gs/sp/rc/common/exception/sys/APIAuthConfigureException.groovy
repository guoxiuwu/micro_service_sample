package com.ebao.gs.sp.rc.common.exception.sys
/**
 * Created by xiuwu.guo on 11/7/2017.
 */
class APIAuthConfigureException extends SystemException {

    APIAuthConfigureException() {
        super()
    }

    APIAuthConfigureException(String message) {
        super(message)
    }

    APIAuthConfigureException(String message, Throwable exception) {
        super(message, exception)
    }
}
