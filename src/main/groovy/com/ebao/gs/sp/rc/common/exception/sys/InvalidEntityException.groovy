package com.ebao.gs.sp.rc.common.exception.sys

import com.ebao.gs.sp.rc.common.exception.sys.SystemException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class InvalidEntityException extends SystemException  {

    InvalidEntityException(){
        super()
    }

    InvalidEntityException(String message) {
        super(message)
    }

    InvalidEntityException(String message, Throwable exception) {
        super(message, exception)
    }

}
