package com.ebao.gs.sp.rc.common.utils

import com.ebao.gs.sp.rc.common.exception.ExceptionUtils
import org.apache.commons.collections.CollectionUtils
import org.springframework.util.Assert

/**
 * Created by xiuwu.guo on 11/2/2017.
 */
class Resp<T> {

    boolean success = false

    List<MessageObj> messages

    T data


    public static final Resp success() {
        new Resp(success: true)
    }

    public static final Resp<T> success(T data) {
        new Resp(success: true, data: data)
    }

    public static final Resp<T> success(T data, List<MessageObj> reminderMessages) {
        new Resp(success: true, data: data, messages: reminderMessages)
    }

    public static final Resp fail(Throwable e) {
        new Resp(success: false, messages: ExceptionUtils.getMessageList(e))
    }

    public static final Resp failWithPlainMessage(String errorCode, String message, List<String> messageParams) {
        new Resp(success: false, messages: [new MessageObj(
                messageErrorCode: errorCode,
                message: message,
                params: messageParams
        )])
    }

    public static final Resp fail(MessageObj messageObj) {
        Assert.notNull(messageObj)
        new Resp(success: false, messages: [messageObj])
    }

    public static final Resp fail(List<MessageObj> messageObjs) {
        Assert.isTrue(CollectionUtils.isNotEmpty(messageObjs))
        new Resp(success: false, messages: messageObjs)
    }


    @Override
    String toString(){
        JSONUtils.toJSON(this)
    }
}
