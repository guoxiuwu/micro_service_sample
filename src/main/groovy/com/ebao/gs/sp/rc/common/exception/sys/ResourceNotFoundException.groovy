package com.ebao.gs.sp.rc.common.exception.sys

/**
 * Created by xiuwu.guo on 11/8/2017.
 */
class ResourceNotFoundException extends SystemException {

    ResourceNotFoundException() {
        super()
    }

    ResourceNotFoundException(String message) {
        super(message)
    }

    ResourceNotFoundException(String message, Throwable exception) {
        super(message, exception)
    }
}
