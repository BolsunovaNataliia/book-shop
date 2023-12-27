package mate.academy.bookshop.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public record UserLoginRequestDto (
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email should be valid")
    @Size(min = 8, max = 20)
    String email,
    @NotBlank
    @Size(min = 8, max = 20, message =
            "The password must be between 8 and 35 symbols long")
    String password
) {
}
