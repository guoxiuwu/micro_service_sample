package com.ebao.gs.sp.rc.common.exception.buis

import com.ebao.gs.sp.rc.common.exception.BaseException

/**
 * Created by xiuwu.guo on 11/2/2017.
 */
class BusinessException extends BaseException {

    // params of error message
    List<Object> params

    BusinessException(String message) {
        super(message)
    }

    BusinessException(String message, List<Object> params) {
        super(message)
        this.params = params
    }

    BusinessException(String message, Throwable exception) {
        super(message, exception)
    }

    BusinessException(String message, List<Object> params, Throwable exception) {
        super(message, exception)
        this.params = params
    }
}
