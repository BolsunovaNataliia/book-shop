package mate.academy.bookshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookshop.validation.PasswordFieldMatch;
import org.hibernate.validator.constraints.Length;

@PasswordFieldMatch(message = "{register.repeatPassword.mismatch}")
@Data
public class UserRegistrationRequestDto {
    private Long id;
    @Email(message = "{register.email.invalid}")
    @NotBlank(message = "{register.email.invalid}")
    @NotNull
    private String email;
    @Length(min = 8, max = 35, message = "{register.password.size}")
    @NotNull
    private String password;
    @Length(min = 8, max = 35)
    @NotNull
    private String repeatPassword;
    @Size(max = 50)
    @NotNull
    private String firstName;
    @Size(max = 50)
    @NotNull
    private String lastName;
    @Size(max = 255)
    private String shippingAddress;
}
