package mate.academy.bookshop.dto.book;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import mate.academy.bookshop.validation.Isbn;

@Data
public class CreateBookRequestDto {
    private Long id;
    @NotNull
    @Size(max = 255)
    private String title;
    @NotNull
    @Size(max = 255)
    private String author;
    @Isbn
    @NotNull
    private String isbn;
    @NotNull
    @Min(0)
    @Max(100000)
    private BigDecimal price;
    @Size(max = 255)
    private String description;
    @Size(max = 255)
    private String coverImage;
    private Set<Long> categoryIds;
}
