package com.ebao.gs.sp.rc.common.utils

import groovy.transform.ToString

/**
 * Created by xiuwu.guo on 11/8/2017.
 *
 * new  MessageObj(
 *    messageLevel = MessageLevel.ERROR,
 *    messageErrorCode = "ERROR_502"
 *    message = "the policy has been issued by %s"
 *    params = ["Jone"]
 * )
 */
@ToString
class MessageObj implements Serializable {

    MessageLevel messageLevel = MessageLevel.ERROR
    String messageErrorCode
    String message
    List<String> params

    static enum MessageLevel {
        INFO, WARN, ERROR
    }
}
