package mate.academy.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart management", description = "Endpoints for managing cart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartConroller {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Add the book to the shopping cart",
            description = "Add the book to the shopping cart")
    public ShoppingCartDto addToCart(@RequestBody @Valid Authentication authentication,
                                     AddToCartRequestDto requestDto) {
        User user = (User)authentication.getPrincipal();
        return shoppingCartService.addToCart(user.getId(), requestDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get a book in the cart by id",
            description = "Get a book in the cart by id")
    public ShoppingCartDto findById(@PathVariable Long id) {
        return shoppingCartService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book from the cart by id",
            description = "Delete a book from the cart by id")
    public void delete(@PathVariable(name = "id") Long bookId) {
        shoppingCartService.deleteById(bookId);
    }
}
