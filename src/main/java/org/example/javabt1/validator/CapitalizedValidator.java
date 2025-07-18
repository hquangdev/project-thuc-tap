package org.example.javabt1.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CapitalizedValidator implements ConstraintValidator<Capitalized, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // Không kiểm tra null/blank ở đây, để @NotBlank lo
        }
        return Character.isUpperCase(value.charAt(0));
    }
}

