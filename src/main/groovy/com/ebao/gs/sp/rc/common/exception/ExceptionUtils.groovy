package com.ebao.gs.sp.rc.common.exception

import com.ebao.gs.sp.rc.common.exception.buis.BusinessException
import com.ebao.gs.sp.rc.common.utils.MessageObj

/**
 * Created by xiuwu.guo on 11/8/2017.
 */
final class ExceptionUtils {

    public static String getErrorCode(Throwable e) {
        String errorCode
        if (e instanceof BaseException) {
            errorCode = ((BaseException) e).getErrorCode()
        } else {
            errorCode = ErrorCode.SYSTEM_ERROR
        }
        return errorCode
    }

    public static String getStackTrace(Throwable ex) {
        StringWriter errors = new StringWriter()
        ex.printStackTrace(new PrintWriter(errors))
        return errors.toString()
    }

    public static String getFirst4000CharsOfStackTrace(Throwable ex) {
        StringWriter errors = new StringWriter()
        ex.printStackTrace(new PrintWriter(errors))
        String s = errors.toString()
        if (s.length() <= 4000) {
            return s
        } else {
            return s.substring(0, 4000)
        }
    }

    public static List<MessageObj> getMessageList(Throwable e) {
        if (!e) return null
        String errorMessage = e.getMessage()
        def params
        if (!errorMessage) {
            errorMessage = e.getCause()?.message
        }
        if (e instanceof BusinessException) {
            params = (BusinessException) e.params
        }
        if (!errorMessage) {
            errorMessage = e.toString()
        }
        def messageList = []
        messageList.add(new MessageObj(
                messageErrorCode: getErrorCode(e),
                message: errorMessage,
                params: params
        ))
        return errorMessage
    }


    public static String getPlainMessage(Throwable e) {
        if (!e) return null
        String errorMessage = e.getMessage()
        if (!errorMessage) {
            errorMessage = e.getCause()?.message
        }
        if (!errorMessage) {
            errorMessage = e.toString()
        }
        return errorMessage
    }


}
