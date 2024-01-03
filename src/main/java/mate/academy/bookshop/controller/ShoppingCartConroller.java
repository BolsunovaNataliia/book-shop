package mate.academy.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.dto.shoppingcart.UpdateQuantityBookRequestDto;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.service.ShoppingCartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management",
        description = "Endpoints for managing shopping cart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartConroller {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Add a cart item to the shopping cart",
            description = "Add a cart item to the shopping cart")
    public ShoppingCartDto addToCart(Authentication authentication,
            @RequestBody @Valid AddToCartRequestDto requestDto) {
        User user = (User)authentication.getPrincipal();
        return shoppingCartService.addToCart(user.getId(), requestDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    @Operation(summary = "Get a user's shopping cart",
            description = "Get a user's shopping cart")
    public ShoppingCartDto findById(Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        return shoppingCartService.findById(user.getId());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping("/cart-items/{id}")
    @Operation(summary = "Update book's quantity by cart item id",
            description = "Update book's quantity by cart item id")
    public ShoppingCartDto updateQuantityByCartItemId(
            Authentication authentication,
            @PathVariable(name = "id") Long cartItemId,
            @RequestBody @Valid UpdateQuantityBookRequestDto requestDto
    ) {
        User user = (User)authentication.getPrincipal();
        return shoppingCartService
                .updateQuantityByCartItemId(user.getId(), cartItemId, requestDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("/cart-items/{id}")
    @Operation(summary = "Delete a book from the shopping cart by cart item id",
            description = "Delete a book from the shopping cart by cart item id")
    public void delete(Authentication authentication,
                       @PathVariable(name = "id") Long cartItemId) {
        User user = (User)authentication.getPrincipal();
        shoppingCartService.deleteCartItemById(user.getId(), cartItemId);
    }
}
