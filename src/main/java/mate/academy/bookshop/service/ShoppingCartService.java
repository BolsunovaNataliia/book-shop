package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(Long userId, AddToCartRequestDto bookRequestDto);

    ShoppingCartDto findById(Long id);

    ShoppingCartDto updateById(Long id);

    void deleteById(Long id);
}
