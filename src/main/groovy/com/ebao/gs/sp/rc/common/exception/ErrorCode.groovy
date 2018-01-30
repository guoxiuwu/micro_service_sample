package com.ebao.gs.sp.rc.common.exception

import groovy.transform.CompileStatic

/**
 * Created by xiuwu.guo on 11/2/2017.
 */
@CompileStatic
class ErrorCode implements Serializable {

    public final static String SYSTEM_ERROR = "000"
    public final static String VALIDATION_EXCEPTION = "019"

    //login error code
    public static final String ACCOUNT_INCORRECT_ERROR = "401.1"
    public static final String ACCOUNT_LOCKED_ERROR = "401.2"
    public static final String ACCOUNT_DISABLED_ERROR = "401.3"
    public static final String ACCOUNT_EXPIRED_ERROR = "401.4"
    public static final String ACCOUNT_CREDENTIAL_EXPIRED_ERROR = "401.5"
    public static final String ACCOUNT_TENANT_DISABLED_ERROR = "401.6"
    public static final String ACCOUNT_PASSWORD_BREAKING_RULE_ERROR = "401.7"
    public static final String ACCOUNT_CAPTCHA_CODE_VERIFICATION_FAILED = "401.8"

}
