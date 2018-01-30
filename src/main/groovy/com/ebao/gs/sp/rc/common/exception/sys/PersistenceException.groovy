package com.ebao.gs.sp.rc.common.exception.sys
/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class PersistenceException extends SystemException {

    PersistenceException(){
        super()
    }

    PersistenceException(String message) {
        super(message)
    }

    PersistenceException(String message, Throwable exception) {
        super(message, exception)
    }

}
