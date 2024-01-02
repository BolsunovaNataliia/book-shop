package mate.academy.bookshop.service;

import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.cart.AddToCartRequestDto;
import mate.academy.bookshop.dto.cart.CartDto;
import mate.academy.bookshop.dto.cart.CartItemDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    @Override
    public CartItemDto save(Long userId, AddToCartRequestDto bookRequestDto) {

        return null;
    }

    @Override
    public CartDto findById(Long id) {
        return null;
    }

    @Override
    public CartDto updateById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
