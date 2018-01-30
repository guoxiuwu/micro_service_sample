package com.ebao.gs.sp.rc.model

import com.ebao.gs.sp.rc.common.model.BaseDataVerEntity
import com.ebao.gs.sp.rc.common.validation.anno.SizeValidate

/**
 * Created by xiuwu.guo on 11/8/2017.
 */
class Email extends BaseDataVerEntity {

    Long emailId
    String subject
    @SizeValidate(max = 500)
    String toList
    @SizeValidate(max = 500, notNull = false)
    String ccList
    @SizeValidate(max = 500, notNull = false)
    String bccList
    @SizeValidate(max = 100, notNull = false)
    String contentTemplateName
    String contentParams
    Byte status
    int retryTimes
    @SizeValidate(max = 2000, notNull = false)
    String lastSendErrorMessage
    Byte priority
    Byte sendBy

    @Override
    Serializable getPK() {
        return emailId
    }
}
