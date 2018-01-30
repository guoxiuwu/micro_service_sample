package com.ebao.gs.sp.rc.common.basic

import com.ebao.gs.sp.rc.model.User

/**
 * Created by xiuwu.guo on 11/2/2017.
 */
final class LoginContext implements Serializable {

    User currentUser

    String fromIp

    boolean isLoginRequest

}
