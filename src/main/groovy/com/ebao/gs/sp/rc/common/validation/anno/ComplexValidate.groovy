package com.ebao.gs.sp.rc.common.validation.anno

import com.ebao.gs.sp.rc.common.validation.ComplexValidator

import javax.validation.Constraint
import javax.validation.Payload
import java.lang.annotation.Documented
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.*
import static java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by xiuwu.guo on 7/11/2016.
 */
@Target([METHOD, FIELD, TYPE])
@Constraint(validatedBy = [ComplexValidator.class])
@Retention(RUNTIME)
@Documented
@Inherited
@interface ComplexValidate {

    public abstract String message() default "The field '{propertyPath}' is invalid";

    public abstract Class<?>[] groups() default [];

    public abstract Class<? extends Payload>[] payload() default [];

    public abstract Class<? extends ComplexValidator> by();

    /**if value is null,didn't validate continue..**/
    public abstract boolean notNull() default true;

}