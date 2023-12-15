package mate.academy.bookshop.repository.book.spec;

import java.util.Arrays;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "price";
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get("price")
                .in(Arrays.stream(params).toArray());
    }
}
