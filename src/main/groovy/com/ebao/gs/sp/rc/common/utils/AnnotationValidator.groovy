package com.ebao.gs.sp.rc.common.utils

import org.hibernate.validator.HibernateValidator
import org.hibernate.validator.HibernateValidatorConfiguration

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import javax.validation.groups.Default

/**
 * Created by xiuwu.guo on 11/6/2017.
 */
class AnnotationValidator {

    private static ValidatorFactory strictValidatorFactory
    private static ValidatorFactory validatorFactory

    static {
        HibernateValidatorConfiguration configuration = Validation.byProvider(
                HibernateValidator.class).configure();
        validatorFactory = configuration.addProperty("hibernate.validator.fail_fast", "true").
                buildValidatorFactory();
        strictValidatorFactory = Validation.buildDefaultValidatorFactory();
    }


    static <T> List<String> validate(T beValidatedObj, boolean withStrict = false, Class<?>... validateGroups = []) {
        ValidatorFactory factory = withStrict ? strictValidatorFactory : validatorFactory
        Validator validator = factory.getValidator()
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(
                beValidatedObj, validateGroups ? validateGroups : Default.class);

        if (constraintViolations) {
            List<String> results = new ArrayList<String>();
            for (Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator(); iterator.hasNext();) {
                ConstraintViolation<T> cons = iterator.next()
                // propertyPath as default variable be used in error message
                results.add(cons.getMessage().toString().replaceAll("\\{propertyPath\\}", (cons.getPropertyPath() ? cons.getPropertyPath() :
                        "").toString()))
            }
            return results
        } else {
            return Collections.EMPTY_LIST
        }
    }
}
