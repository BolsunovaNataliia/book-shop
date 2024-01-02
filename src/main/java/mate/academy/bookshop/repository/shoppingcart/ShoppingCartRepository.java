package mate.academy.bookshop.repository.shoppingcart;

import mate.academy.bookshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository
        extends JpaRepository<ShoppingCart, Long>, JpaSpecificationExecutor<ShoppingCart> {
    Optional<ShoppingCart> findByUserId(Long userId);
//    @Query(
//    "SELECT sc FROM ShoppingCart sc JOIN Fetch sc.cartItems ci WHERE ci.id = :shoppingCartId")
//    List<ShoppingCart> findById(@PathVariable(name = "id") Long categoryId);
}
