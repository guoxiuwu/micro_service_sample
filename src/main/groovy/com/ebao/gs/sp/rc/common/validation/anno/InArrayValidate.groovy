package com.ebao.gs.sp.rc.common.validation.anno

import com.ebao.gs.sp.rc.common.validation.InArrayValidator

import javax.validation.Constraint
import javax.validation.Payload
import java.lang.annotation.Documented
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.FIELD
import static java.lang.annotation.ElementType.METHOD
import static java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by xiuwu.guo on 7/11/2016.
 */

@Target([METHOD, FIELD])
@Constraint(validatedBy = [ InArrayValidator.class ])
@Retention(RUNTIME)
@Documented
@Inherited
@interface InArrayValidate {

    public abstract String message() default "The field '{propertyPath}' is invalid";

    public abstract Class<?>[] groups() default [];

    public abstract Class<? extends Payload>[] payload() default [];

    public abstract int[] array();

    /**if value is null,didn't validate continue..**/
    public abstract boolean notNull() default true;
}
