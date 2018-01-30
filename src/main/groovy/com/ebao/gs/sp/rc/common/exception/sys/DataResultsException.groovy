package com.ebao.gs.sp.rc.common.exception.sys

import com.ebao.gs.sp.rc.common.exception.sys.SystemException

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class DataResultsException extends SystemException {

    DataResultsException(){
        super()
    }

    DataResultsException(String message) {
        super(message)
    }

    DataResultsException(String message, Throwable exception) {
        super(message, exception)
    }

}
