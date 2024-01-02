package mate.academy.bookshop.dto.shoppingcart;

import java.util.Set;

public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private Set<CartItemDto> cartItems;
}
