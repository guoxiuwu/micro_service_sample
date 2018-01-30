package com.ebao.gs.sp.rc.common.validation

import com.ebao.gs.sp.rc.common.validation.anno.InRangeValidate

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Created by xiuwu.guo on 7/11/2016.
 */
class InRangeValidator implements ConstraintValidator<InRangeValidate, Object> {

    private int min
    private int max
    private boolean notNull

    @Override
    void initialize(InRangeValidate inRangeValidate) {
        this.min = inRangeValidate.min()
        this.max = inRangeValidate.max()
        this.notNull = inRangeValidate.notNull()
    }

    @Override
    boolean isValid(Object object, ConstraintValidatorContext context) {
        if (!(object instanceof Number)) {
            return false
        }
        if (notNull) {
            if (object == null || object.toString().length() == 0) {
                return false
            } else {
                Number value = (Number) object
                return value >= min && value <= max;
            }
        } else {
            if (object == null || object.toString().length() == 0) {
                return true
            } else {
                int value = (Number) object
                return value >= min && value <= max;
            }
        }
    }

}
