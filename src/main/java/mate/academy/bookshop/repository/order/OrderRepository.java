package mate.academy.bookshop.repository.order;

import java.util.Optional;
import mate.academy.bookshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface OrderRepository
        extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Optional<Order> findAllByUserId(@PathVariable(name = "id") Long userId);
}
