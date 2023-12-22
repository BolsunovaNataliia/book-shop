package mate.academy.bookshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements
        ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value,
                           ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object firstFieldValue = BeanUtils.getProperty(value, firstFieldName);
            Object secondFieldValue = BeanUtils.getProperty(value, secondFieldName);

            return firstFieldValue != null && firstFieldValue.equals(secondFieldValue);
        } catch (Exception e) {
            throw new RuntimeException("Method arguments are invalid");
        }
    }
}
