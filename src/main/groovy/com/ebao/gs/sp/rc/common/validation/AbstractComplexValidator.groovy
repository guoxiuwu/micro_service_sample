package com.ebao.gs.sp.rc.common.validation

import javax.validation.ConstraintValidatorContext

/**
 * Created by xiuwu.guo on 7/11/2016.
 */
abstract class AbstractComplexValidator {

    public abstract boolean validate(Object beValidated)

    protected String errorMessage

    public void constructMessage(String str) {
        errorMessage = str
    }

    public final void buildMessage(ConstraintValidatorContext ct) {
        ct.buildConstraintViolationWithTemplate(errorMessage ? errorMessage :
                " is invalid").addConstraintViolation()
    }
}
