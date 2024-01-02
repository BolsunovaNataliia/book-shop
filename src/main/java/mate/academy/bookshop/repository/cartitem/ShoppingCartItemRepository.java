package mate.academy.bookshop.repository.cartitem;

import mate.academy.bookshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShoppingCartItemRepository
        extends JpaRepository<CartItem, Long>, JpaSpecificationExecutor<CartItem> {

}
