package mate.academy.bookshop.dto.cart;

import java.util.Set;

public class CartDto {
    private Long id;
    private Long userId;
    private Set<CartItemDto> cartItems;
}
