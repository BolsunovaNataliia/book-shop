package mate.academy.bookshop.dto.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotNull
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String description;
}
