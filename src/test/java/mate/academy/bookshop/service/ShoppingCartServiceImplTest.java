package mate.academy.bookshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;
import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.mapper.ShoppingCartMapper;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.CartItem;
import mate.academy.bookshop.model.ShoppingCart;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.repository.book.BookRepository;
import mate.academy.bookshop.repository.cartitem.CartItemRepository;
import mate.academy.bookshop.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.bookshop.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceImplTest {
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private ShoppingCartMapper shoppingCartMapper;
    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Test
    @DisplayName("Adding cart item to shopping cart")
    public void addToCart_WithUser_ReturnShoppingCartDto() {
        // Given
        User user = new User()
                .setId(1L)
                .setEmail("john@gmail.com");
        Book book = new Book()
                .setId(1L);
        cartItemRepository.save(new CartItem().setId(1L));
        ShoppingCart shoppingCart = new ShoppingCart()
                .setId(1L)
                .setUser(user);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        AddToCartRequestDto requestDto = new AddToCartRequestDto()
                .setBookId(1L)
                .setQuantity(10);
        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        Mockito.when(bookRepository.findById(book.getId()))
                .thenReturn(Optional.of(book));
        Mockito.when(shoppingCartRepository.findByUserId(user.getId()))
                .thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartMapper.toDto(shoppingCart))
                .thenReturn(shoppingCartDto);

        // When
        ShoppingCartDto actualShoppingCartDto =
                shoppingCartService.addToCart(user.getId(), requestDto);

        // Then
        assertNotNull(actualShoppingCartDto);
        assertEquals(shoppingCartDto, actualShoppingCartDto);
    }
}
