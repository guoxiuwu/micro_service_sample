package com.ebao.gs.sp.rc.common.exception.sys

import com.ebao.gs.sp.rc.common.exception.sys.SystemException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class OptimisticLockException extends SystemException {

    OptimisticLockException(){
        super()
    }

    OptimisticLockException(String message) {
        super(message)
    }

    OptimisticLockException(String message, Throwable exception) {
        super(message, exception)
    }

}
