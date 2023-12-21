package mate.academy.bookshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookshop.validation.FieldMatch;

@FieldMatch(first = "password",
        second = "repeatPassword",
        message = "The passwords must match")
@Data
public class UserRegistrationRequestDto {
    private Long id;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email should be valid")
    @Size(max = 64)
    private String email;
    @NotBlank
    @Size(min = 8, max = 35, message =
            "The password must be between 8 and 35 symbols long")
    private String password;
    @NotBlank
    @Size(min = 8, max = 35)
    private String repeatPassword;
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @Size(max = 255)
    private String shippingAddress;
}
