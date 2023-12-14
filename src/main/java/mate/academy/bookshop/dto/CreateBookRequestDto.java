package mate.academy.bookshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import mate.academy.bookshop.validation.Isbn;

@Data
public class CreateBookRequestDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @Isbn
    @NotNull
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    private String coverImage;
}
