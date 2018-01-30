package com.ebao.gs.sp.rc.common.exception

/**
 * Created by xiuwu.guo on 11/2/2017.
 */
class BaseException extends RuntimeException {

    protected String errorCode

    BaseException() {
        super()
    }

    BaseException(String message) {
        super(message)
    }

    BaseException(String message, Throwable exception) {
        super(message, exception)
    }

    String getErrorCode() {
        return errorCode
    }

    String toString() {
        "ERR-${errorCode}: ${message}"
    }
}
