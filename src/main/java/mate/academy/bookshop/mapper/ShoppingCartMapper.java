package mate.academy.bookshop.mapper;

import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.dto.shoppingcart.UpdateQuantityBookRequestDto;
import mate.academy.bookshop.model.ShoppingCart;

public interface ShoppingCartMapper {
    ShoppingCartDto toDto(ShoppingCart shippingCart);

    ShoppingCart toModel(AddToCartRequestDto addBookRequestDto);

    ShoppingCart toModel(UpdateQuantityBookRequestDto updateRequestDto);
}
