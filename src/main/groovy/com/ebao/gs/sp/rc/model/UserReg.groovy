package com.ebao.gs.sp.rc.model

import com.ebao.gs.sp.rc.common.model.BaseAuditEntity
import com.ebao.gs.sp.rc.common.validation.anno.SizeValidate
import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * Created by xiuwu.guo on 11/8/2017.
 */
class UserReg extends BaseAuditEntity {

    Long userRegId
    @SizeValidate(max = 20)
    String userName
    @SizeValidate(max = 400)
    String realName
    @SizeValidate(max = 200)
    String email
    @SizeValidate(max = 50)
    String mobile
    @SizeValidate(max = 50)
    String telPhone
    @JsonIgnore
    @SizeValidate(max = 32)
    String passWord

    boolean verifyEmailSend
    Byte verifyEmailRes

    UserProfile userProfile

    @Override
    Serializable getPK() {
        return userRegId
    }
}
