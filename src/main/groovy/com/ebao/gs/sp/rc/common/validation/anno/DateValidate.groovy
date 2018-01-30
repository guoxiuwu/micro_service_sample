package com.ebao.gs.sp.rc.common.validation.anno

import com.ebao.gs.sp.rc.common.validation.DateValidator

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
@Constraint(validatedBy = [ DateValidator.class ])
@Retention(RUNTIME)
@Documented
@Inherited
@interface DateValidate {

    public enum Format {

        DEFAULT("dd/MM/yyyy"),
        DEFAULT_PRECISE("dd/MM/yyyy HH:mm:ss")

        private Format(String fm) {
            this.fm = fm;
        }

        private final String fm;

        public String getFm() {
            return fm;
        }
    }

    public abstract String message() default "The field '{propertyPath}' is invalid";

    public abstract Class<?>[] groups() default [];

    public abstract Class<? extends Payload>[] payload() default [];

    public abstract String before() default "";

    public abstract String after() default "";

    public abstract boolean beforeNow() default false;

    public abstract boolean afterNow() default false;

    public Format format() default Format.DEFAULT;

    /**if value is null,didn't validate continue..**/
    public abstract boolean notNull() default true;

}
