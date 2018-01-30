package com.ebao.gs.sp.rc.common.exception.sys

import com.ebao.gs.sp.rc.common.exception.BaseException
import com.ebao.gs.sp.rc.common.exception.ErrorCode

/**
 * Created by xiuwu.guo on 11/8/2017.
 */
class SystemException extends BaseException {

    SystemException() {
        super()
        errorCode = ErrorCode.SYSTEM_ERROR
    }

    SystemException(String message) {
        super(message)
        errorCode = ErrorCode.SYSTEM_ERROR
    }

    SystemException(String message, Exception exception) {
        super(message, exception)
        errorCode = ErrorCode.SYSTEM_ERROR
    }
}
