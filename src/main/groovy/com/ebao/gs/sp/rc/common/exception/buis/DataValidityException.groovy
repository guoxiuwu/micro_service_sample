package com.ebao.gs.sp.rc.common.exception.buis

import com.ebao.gs.sp.rc.common.exception.ErrorCode

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class DataValidityException extends BusinessException {

    DataValidityException() {
        super()
        this.errorCode = ErrorCode.VALIDATION_EXCEPTION
    }

    DataValidityException(String message) {
        super(message)
        this.errorCode = ErrorCode.VALIDATION_EXCEPTION
    }

    DataValidityException(String message, Throwable exception) {
        super(message, exception)
        this.errorCode = ErrorCode.VALIDATION_EXCEPTION
    }
}
