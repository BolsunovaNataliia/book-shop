package mate.academy.bookshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mate.academy.bookshop.dto.UserRegistrationRequestDto;

public class PasswordMatchValidator implements
        ConstraintValidator<PasswordFieldMatch, UserRegistrationRequestDto> {
    @Override
    public void initialize(PasswordFieldMatch p) {
    }

    public boolean isValid(UserRegistrationRequestDto requestDto, ConstraintValidatorContext c) {
        String password = requestDto.getPassword();
        String repeatPassword = requestDto.getRepeatPassword();

        if (password == null || !password.equals(repeatPassword)) {
            return false;
        }
        return true;
    }
}
