package com.ebao.gs.sp.rc.common.exception.sys

import com.ebao.gs.sp.rc.common.exception.sys.SystemException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class InvalidModelException extends SystemException {

    InvalidModelException(){
        super()
    }

    InvalidModelException(String message) {
        super(message)
    }

    InvalidModelException(String message, Throwable exception) {
        super(message, exception)
    }

}
