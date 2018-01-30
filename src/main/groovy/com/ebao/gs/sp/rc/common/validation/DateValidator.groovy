package com.ebao.gs.sp.rc.common.validation

import com.ebao.gs.sp.rc.common.validation.anno.DateValidate

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import java.text.SimpleDateFormat

/**
 * Created by xiuwu.guo on 7/11/2016.
 */
class DateValidator implements ConstraintValidator<DateValidate, Object> {

    private String before
    private String after
    private boolean beforeNow
    private boolean afterNow
    private DateValidate.Format format
    private boolean notNull


    @Override
    void initialize(DateValidate dateValidate) {
        this.format = dateValidate.format()
        this.before = dateValidate.before()
        this.after = dateValidate.after()
        this.afterNow = dateValidate.afterNow()
        this.beforeNow = dateValidate.beforeNow()
        this.notNull = dateValidate.notNull()

    }


    @Override
    boolean isValid(Object object, ConstraintValidatorContext ct) {
        if (!(object instanceof String)) {
            throw new IllegalArgumentException("The DateValidator must be defined on String field")
        }
        if (notNull) {
            if (object == null || object.toString().length() == 0) {
                return false
            } else {
                Date dt = castDate(String.valueOf(object))
                if (dt != null) {
                    return isValidRange(dt, ct)
                }
            }
        } else {
            if (object == null || object.toString().length() == 0) {
                return true
            } else {
                Date dt = castDate(String.valueOf(object))
                if (dt != null) {
                    return isValidRange(dt, ct)
                }
            }
        }
        return false
    }


    private Date castDate(String str) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(
                    format.getFm())
            sf.setLenient(false)
            return sf.parse(str)
        } catch (Exception e) {
            return null
        }
    }


    private boolean isValidRange(Date value, ConstraintValidatorContext ct) {

        SimpleDateFormat sf = new SimpleDateFormat(
                format.getFm())

        if (before) {
            Date date = null
            try {
                sf.setLenient(false)
                date = sf.parse(before)
                boolean beforeDate = value.before(date)
                if (!beforeDate) {
                    // customize info
                    return false
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("the before value of DateValidate is invalid data String")
            }
        }

        if (after) {
            Date date = null
            try {
                date = sf.parse(after)
                boolean afterDate = value.after(date)
                if (!afterDate) {
                    // customize info
                    return false
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("the after value of DateValidate is invalid data String");
            }
        }

        Date currentDate = new Date()// current Date
        if (this.beforeNow) {
            boolean beforeNow = value.before(currentDate)
            if (!beforeNow) {
                // customize info
                return false
            }
        }

        if (this.afterNow) {
            boolean afterNow = value.after(currentDate);
            if (!afterNow) {
                // customize info
                return false
            }
        }
        return true
    }
}
