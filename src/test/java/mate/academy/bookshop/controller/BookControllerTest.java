package mate.academy.bookshop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import mate.academy.bookshop.dto.book.BookDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerTest {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    @SneakyThrows
    private static void setup(@Autowired DataSource dataSource,
                      @Autowired WebApplicationContext applicationContext) {
        teardown(dataSource);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource(
                            "database/add-three-default-books.sql")
            );
        }
    }

    @AfterAll
    private static void afterAll(
            @Autowired DataSource dataSource
    ) {
        teardown(dataSource);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    @DisplayName("Get all books")
    public void findAll_WithBooks_ShouldReturnListOfBooks() throws Exception {
        // Given
        BookDto book1 = new BookDto();
        book1.setId(1L);
        book1.setTitle("A short course in the history of Ukraine");
        book1.setAuthor("Y. Hrytsak");
        book1.setIsbn("234-1-235-6-1");
        book1.setPrice(BigDecimal.valueOf(100000, 2));
        book1.setDescription("About Ukrainian history");
        book1.setCoverImage("Cover image 1");
        book1.setCategoryIds(Set.of(1L));

        BookDto book2 = new BookDto();
        book2.setId(2L);
        book2.setTitle("The Hobbit");
        book2.setAuthor("J.R.R. Tolkien");
        book2.setIsbn("234-1-244-6-7");
        book2.setPrice(BigDecimal.valueOf(110000, 2));
        book2.setDescription("About fantasy story");
        book2.setCoverImage("Cover image 2");
        book2.setCategoryIds(Set.of(2L));

        BookDto book3 = new BookDto();
        book3.setId(3L);
        book3.setTitle("The Girl with the Dragon Tattoo");
        book3.setAuthor("Stieg Larsson");
        book3.setIsbn("111-1-235-1-9");
        book3.setPrice(BigDecimal.valueOf(120000, 2));
        book3.setDescription("About the history of detective research");
        book3.setCoverImage("Cover image 3");
        book3.setCategoryIds(Set.of(3L));

        List<BookDto> expectedBookList = new ArrayList<>();
        expectedBookList.add(book1);
        expectedBookList.add(book2);
        expectedBookList.add(book3);

        // When
        MvcResult result = mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        List<BookDto> actualBookList = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );
        assertEquals(expectedBookList, actualBookList);
    }

    @SneakyThrows
    private static void teardown(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource(
                            "database/remove-added-books.sql")
            );
        }
    }
}
