package com.ebao.gs.sp.rc.common.validation

import com.ebao.gs.sp.rc.common.validation.anno.ComplexValidate
import groovy.util.logging.Slf4j

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Created by xiuwu.guo on 7/11/2016.
 */
@Slf4j
class ComplexValidator implements ConstraintValidator<ComplexValidate, Object> {

    private Class<?> customizationValidator
    private boolean notNull

    @Override
    void initialize(ComplexValidate complexValidate) {
        this.customizationValidator = complexValidate.by()
        this.notNull = complexValidate.notNull()
    }

    @Override
    boolean isValid(Object object, ConstraintValidatorContext context) {
        if (notNull) {
            if (object == null || object.toString().length() == 0) {
                return false
            } else {
                try {
                    AbstractComplexValidator validator = (AbstractComplexValidator)
                    customizationValidator.newInstance()
                    if (!validator.validate(object)) {
                        validator.buildMessage(context)
                    } else {
                        return true
                    }
                } catch (Exception e) {
                    log.error("error happend when using ComplexValidator:${customizationValidator}", e)
                }
            }
        } else {
            if (object == null || object.toString().length() == 0) {
                return true
            } else {
                try {
                    AbstractComplexValidator validator = (AbstractComplexValidator)
                    customizationValidator.newInstance()
                    if (!validator.validate(object)) {
                        validator.buildMessage(context)
                    } else {
                        return true
                    }
                } catch (Exception e) {
                    log.error("error happend when using ComplexValidator:${customizationValidator}", e);
                }
            }
        }
    }
}
