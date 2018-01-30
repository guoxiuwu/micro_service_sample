package com.ebao.gs.sp.rc.common.security

import groovy.transform.CompileStatic

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
@CompileStatic
class MyStaticParams implements Serializable {

    static final class PATH_REGEX {
        static final String LOGIN = "/api/users/login"
        static final String NO_AUTH = "/"
        static final String CSS = "/css/**"
        static final String JS = "/js/**"
        static final String HTML = "/html/**"
        static final String LESS = "/less/**"
        static final String JSON = "/json/**"
        static final String IMG = "/img/**"
        static final String LOGOUT = "/logout"
        static final String FONTS = "/fonts/**"
        static final String LOGO = "/favicon.ico"
        static final String THIRD_RESOURCE = "/3rd-libs/**"

        static final String[] NO_AUTH_ALL = [NO_AUTH, CSS, JS, HTML, LESS, JSON, IMG, FONTS, LOGO, THIRD_RESOURCE, LOGIN, LOGOUT]
    }

    static final class HTTP_REQUEST_PARAM {
        static final String LANG = "lang"
        static final String CAPTCHA_CODE_KEY = "CaptchaCode"
        static final String AUTHORIZATION = "Authorization"
        static final String ACCESS_TOKEN = "accessToken"
    }

    static final class USER_ROLE {
        static final String ADMIN = "ADMIN"
        static final String B2C_USER = "B2CUSER"
    }

}
