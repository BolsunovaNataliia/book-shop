package mate.academy.bookshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import mate.academy.bookshop.dto.book.BookDto;
import mate.academy.bookshop.mapper.BookMapper;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.repository.book.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;

    @Test
    public void getAll_WithBook_ReturnList() {
        // Given
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Java 11");
        List<Book> books = List.of(book);
        Pageable pageable = PageRequest.of(0, 5);
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Java 11");
        bookDto.setAuthor("Somebody");
        Page<Book> page = new PageImpl<>(books, pageable, books.size());
        List<BookDto> expected = List.of(bookDto);

        Mockito.when(bookRepository.findAll(pageable)).thenReturn(page);
        Mockito.when(bookMapper.toDto(book)).thenReturn(bookDto);

        // When
        List<BookDto> actual = bookService.findAll(pageable);

        // Then
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
    }
}
