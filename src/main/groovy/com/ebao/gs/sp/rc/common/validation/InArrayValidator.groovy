package com.ebao.gs.sp.rc.common.validation

import com.ebao.gs.sp.rc.common.validation.anno.InArrayValidate

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Created by xiuwu.guo on 7/11/2016.
 */
class InArrayValidator implements ConstraintValidator<InArrayValidate, Object> {

    private int[] array
    private boolean notNull


    @Override
    void initialize(InArrayValidate inArrayValidate) {
        this.array = inArrayValidate.array()
        this.notNull = inArrayValidate.notNull()
    }

    @Override
    boolean isValid(Object object, ConstraintValidatorContext context) {
        if (!(object instanceof Integer)) {
            return false
        }
        if (notNull) {
            if (object == null || object.toString().length() == 0) {
                return false
            } else {
                Integer value = (Integer) object
                if (array?.find(value.intValue())) {
                    return true
                }
            }
        } else {
            if (object == null || object.toString().length() == 0) {
                return true
            } else {
                Integer value = (Integer) object
                if (array?.find(value.intValue())) {
                    return true
                }
            }
        }
        return false
    }
}
