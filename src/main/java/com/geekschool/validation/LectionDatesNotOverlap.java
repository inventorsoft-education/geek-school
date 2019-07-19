package com.geekschool.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = LectionDatesNotOverlapValidator.class)
public @interface LectionDatesNotOverlap {
    String message() default "Multiple lectures cannot be at the same time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
