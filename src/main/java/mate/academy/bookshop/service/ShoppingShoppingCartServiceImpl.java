package mate.academy.bookshop.service;

import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.exception.EntityNotFoundException;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.CartItem;
import mate.academy.bookshop.model.ShoppingCart;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.repository.book.BookRepository;
import mate.academy.bookshop.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.bookshop.repository.cartitem.ShoppingCartItemRepository;
import mate.academy.bookshop.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public ShoppingCartDto addToCart(Long userId, AddToCartRequestDto requestDto) {
        Long bookId = requestDto.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "There is not found book with id " + bookId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "There is not found user with id " + userId));
        shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user);
                    shoppingCartRepository.save(shoppingCart);
                    return shoppingCart;
                });

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem.setBook(book);
        return null;
    }

    @Override
    public ShoppingCartDto findById(Long id) {
        return null;
    }

    @Override
    public ShoppingCartDto updateById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
