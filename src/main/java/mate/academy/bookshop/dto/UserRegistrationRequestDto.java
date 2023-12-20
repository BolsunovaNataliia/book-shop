package mate.academy.bookshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookshop.validation.PasswordFieldMatch;

@PasswordFieldMatch(message = "{registration.repeatPassword.mismatch}")
@Data
public class UserRegistrationRequestDto {
    private Long id;
    @Email(message = "{registration.email.invalid}")
    @NotEmpty(message = "{registration.email.invalid}")
    @Size(max = 64)
    @NotNull
    private String email;
    @Size(min = 8, max = 35, message = "{registration.password.size}")
    @NotNull
    private String password;
    @Size(min = 8, max = 35)
    @NotNull
    private String repeatPassword;
    @NotEmpty
    @Size(max = 50)
    @NotNull
    private String firstName;
    @NotEmpty
    @Size(max = 50)
    @NotNull
    private String lastName;
    @NotEmpty
    @Size(max = 255)
    private String shippingAddress;
}
