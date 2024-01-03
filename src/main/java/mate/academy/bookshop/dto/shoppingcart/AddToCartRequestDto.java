package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long bookId;
    @Min(value = 1, message = "can't be less than 1")
    private int quantity;
}
