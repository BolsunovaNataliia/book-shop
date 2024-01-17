package mate.academy.bookshop.repository.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.Category;
import mate.academy.bookshop.repository.category.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Get all books by category id")
    public void findAllByCategoryId_WithOneCategory_ShouldReturnListOfBooks() {
        // Given
        Category category = new Category();
        category.setName("Educational");
        category.setDescription("Books for learning");
        categoryRepository.save(category);

        Book book1 = new Book();
        book1.setTitle("Java for Dummies");
        book1.setAuthor("Barry A. Burd");
        book1.setIsbn("234-1-235-6-1");
        book1.setPrice(BigDecimal.valueOf(1020));
        book1.setDescription("It is a great beginner’s guide to Java programming");
        book1.setCoverImage("https://m.media-amazon.com/images/W/MEDIAX_792452-T1"
                + "/images/I/51Gck4g+y9L._SX342_SY445_.jpg");
        book1.setCategories(Set.of(category));
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Head First Design Patterns ");
        book2.setAuthor("Eric Freeman");
        book2.setIsbn("234-1-242-6-7");
        book2.setPrice(BigDecimal.valueOf(1800));
        book2.setDescription("Understanding design patterns is a valuable skill for"
                + " any Java programmer out there");
        book2.setCoverImage("https://m.media-amazon.com/images/W/MEDIAX_792452-T1/images/I/51rmlxN57sL._SX342_SY445_.jpg");
        book2.setCategories(Set.of(category));
        bookRepository.save(book2);

        // When
        List<Book> actual = bookRepository.findAllByCategoryId(category.getId());

        // Then
        assertFalse(actual.isEmpty());
        assertEquals(2, actual.size());
        assertEquals(List.of(book1, book2), actual);
    }
}
