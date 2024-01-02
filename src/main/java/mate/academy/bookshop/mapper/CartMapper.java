package mate.academy.bookshop.mapper;

import mate.academy.bookshop.dto.cart.AddToCartRequestDto;
import mate.academy.bookshop.dto.cart.CartDto;
import mate.academy.bookshop.dto.cart.UpdateQuantityBookRequestDto;
import mate.academy.bookshop.model.ShoppingCart;

public interface CartMapper {
    CartDto toDto(ShoppingCart shippingCart);

    ShoppingCart toModel(AddToCartRequestDto addBookRequestDto);

    ShoppingCart toModel(UpdateQuantityBookRequestDto updateRequestDto);
}
