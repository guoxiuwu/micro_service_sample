package com.ebao.gs.sp.rc.common.exception

import com.ebao.gs.sp.rc.common.exception.buis.BusinessException
import com.ebao.gs.sp.rc.common.utils.Resp
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.servlet.http.HttpServletRequest

/**
 * Created by xiuwu.guo on 11/2/2017.
 */
@Slf4j
@ControllerAdvice
class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(HttpServletRequest request, BusinessException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Resp.fail(e))
    }
}
