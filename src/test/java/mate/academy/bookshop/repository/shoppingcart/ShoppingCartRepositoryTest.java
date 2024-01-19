package mate.academy.bookshop.repository.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;
import mate.academy.bookshop.exception.EntityNotFoundException;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.CartItem;
import mate.academy.bookshop.model.Category;
import mate.academy.bookshop.model.ShoppingCart;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.repository.book.BookRepository;
import mate.academy.bookshop.repository.cartitem.CartItemRepository;
import mate.academy.bookshop.repository.category.CategoryRepository;
import mate.academy.bookshop.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShoppingCartRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    @DisplayName("Get shopping cart by user id")
    public void findByUserId_WithUser_return_ShoppingCart() {
        // Given
        Category category = new Category()
                .setId(1L);
        categoryRepository.save(category);
        Book book = new Book()
                .setId(1L)
                .setCategories(Set.of(category));
        bookRepository.save(book);
        CartItem cartItem = new CartItem()
                .setId(1L)
                .setBook(book);
        User user = new User()
                .setId(1L);
        userRepository.save(user);
        ShoppingCart shoppingCart = new ShoppingCart()
                .setId(1L)
                .setUser(user);
        shoppingCartRepository.save(shoppingCart);
        cartItem.setShoppingCart(shoppingCart);
        shoppingCart.setCartItems(Set.of(cartItem));
        cartItemRepository.save(cartItem);
        shoppingCartRepository.save(shoppingCart);

        // When
        ShoppingCart actual = shoppingCartRepository
                .findByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException(
                        "There is not found user with id " + user.getId()));

        // Then
        assertNotNull(actual);
        assertEquals(shoppingCart, actual);
    }
}
