package mate.academy.bookshop.dto.shoppingcart;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long bookId;
    private int quantity;
}
