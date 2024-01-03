package mate.academy.bookshop.dto.shoppingcart;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateQuantityBookRequestDto {
    @Min(0)
    private int quantity;
}
