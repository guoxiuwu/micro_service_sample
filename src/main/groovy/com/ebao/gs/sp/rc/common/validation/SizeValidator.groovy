package com.ebao.gs.sp.rc.common.validation

import com.ebao.gs.sp.rc.common.validation.anno.SizeValidate

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Created by xiuwu.guo on 7/11/2016.
 */
class SizeValidator implements ConstraintValidator<SizeValidate, Object> {

    private int min
    private int max
    private boolean notNull

    @Override
    void initialize(SizeValidate sizeValidate) {
        this.min = sizeValidate.min()
        this.max = sizeValidate.max()
        this.notNull = sizeValidate.notNull()
    }

    @Override
    boolean isValid(Object object, ConstraintValidatorContext context) {
        if (notNull) {
            if (object == null || object.toString().length() == 0) {
                return false
            } else {
                String value = object.toString()
                return value.length() >= min && value.length() <= max
            }
        } else {
            if (object == null || object.toString().length() == 0) {
                return true
            } else {
                String value = object.toString()
                return value.length() >= min && value.length() <= max
            }
        }
    }
}
