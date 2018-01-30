package com.ebao.gs.sp.rc.common.exception.sys

import com.ebao.gs.sp.rc.common.exception.sys.SystemException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class EntityNotFoundException extends SystemException {

    EntityNotFoundException(){
        super()
    }

    EntityNotFoundException(String message) {
        super(message)
    }

    EntityNotFoundException(String message, Throwable exception) {
        super(message, exception)
    }
}
