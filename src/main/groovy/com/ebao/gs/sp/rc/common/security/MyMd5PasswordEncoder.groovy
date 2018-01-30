package com.ebao.gs.sp.rc.common.security

import com.ebao.gs.sp.rc.common.basic.AppContext
import com.ebao.gs.sp.rc.common.exception.other.PasswordCheckRuleException
import org.springframework.security.authentication.encoding.Md5PasswordEncoder

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
final class MyMd5PasswordEncoder extends Md5PasswordEncoder {

    private final String REGEXP_MUST_CONTAIN_UPPERCASE_LETTERS = "^.*[A-Z]+.*"
    private final String REGEXP_MUST_CONTAIN_LOWERCASE_LETTERS = "^.*[a-z]+.*"
    private final String REGEXP_MUST_CONTAIN_NUMBERS = "^.*[0-9]+.*"
    private final String REGEXP_LENGTH_MUST_GREATER_THAN_8 = "^.{8,}"

    private final boolean isPasswordLegal(String password) {
        boolean isContainUppercaseLetters = password.matches(REGEXP_MUST_CONTAIN_UPPERCASE_LETTERS)
        boolean isContainLowercaseLetters = password.matches(REGEXP_MUST_CONTAIN_LOWERCASE_LETTERS)
        boolean isContainNumbers = password.matches(REGEXP_MUST_CONTAIN_NUMBERS)
        boolean isLengthLegal = password.matches(REGEXP_LENGTH_MUST_GREATER_THAN_8)
        return isContainUppercaseLetters &&
                isContainLowercaseLetters &&
                isContainNumbers && isLengthLegal
    }

    @Override
    boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        if (AppContext.instance.loginContext().isLoginRequest) {
            if (isPasswordLegal(rawPass)) {
                return super.isPasswordValid(encPass, rawPass, salt)
            } else {
                throw new PasswordCheckRuleException('The password must contain uppercase letters,lowercase letters, numbers and must be longer than 8')
            }
        }
        return org.springframework.security.authentication.encoding.PasswordEncoderUtils.equals(encPass, rawPass)
    }
}
