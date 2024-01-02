package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.cart.AddToCartRequestDto;
import mate.academy.bookshop.dto.cart.CartDto;
import mate.academy.bookshop.dto.cart.CartItemDto;

public interface CartService {
    CartItemDto save(Long userId, AddToCartRequestDto bookRequestDto);

    CartDto findById(Long id);

    CartDto updateById(Long id);

    void deleteById(Long id);
}
