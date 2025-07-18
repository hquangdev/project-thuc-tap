package org.example.javabt1.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CapitalizedValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Capitalized {
    String message() default "Chữ cái đầu phải viết hoa";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}