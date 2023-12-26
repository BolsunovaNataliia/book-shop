package mate.academy.bookshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email should be valid")
    @Size(max = 64)
    private String email;
    @NotBlank
    @Size(min = 8, max = 35, message =
            "The password must be between 8 and 35 symbols long")
    private String password;
}
