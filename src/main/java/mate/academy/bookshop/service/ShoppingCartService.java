package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.dto.shoppingcart.UpdateQuantityBookRequestDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(Long userId, AddToCartRequestDto bookRequestDto);

    ShoppingCartDto findById(Long userId);

    ShoppingCartDto updateQuantityByCartItemId(
            Long userId, Long cartItemId, UpdateQuantityBookRequestDto requestDto);

    ShoppingCartDto deleteCartItemById(Long userId, Long cartItemId);
}
