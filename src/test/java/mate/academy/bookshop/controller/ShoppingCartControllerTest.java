package mate.academy.bookshop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.sql.Connection;
import java.util.Set;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import mate.academy.bookshop.dto.shoppingcart.AddToCartRequestDto;
import mate.academy.bookshop.dto.shoppingcart.ShoppingCartDto;
import mate.academy.bookshop.mapper.ShoppingCartMapper;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.CartItem;
import mate.academy.bookshop.model.Category;
import mate.academy.bookshop.model.Role;
import mate.academy.bookshop.model.ShoppingCart;
import mate.academy.bookshop.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShoppingCartControllerTest {
    protected static MockMvc mockMvc;
    private static final Long ADMIN_ROLE_ID = 1L;
    private static final Long USER_ID = 4L;
    private static final Long USER_ROLE_ID = 2L;
    private static final String CLASS_PATH_RESOURCE_ADD =
            "database/add-shopping-cart.sql";
    private static final String CLASS_PATH_RESOURCE_DELETE =
            "database/remove-added-shopping-cart.sql";
    private static final String USER_EMAIL = "john@gmail.com";
    private static final String USER_FIRST_NAME = "John";
    private static final String USER_LAST_NAME = "Fisher";
    private static final String USER_PASSWORD =
            "$2a$10$fRCKtHfKmoKkzXByokmM6.FVmatskXfInb.IYBUI1ukvDBjN4EqGG";
    private static final String USER_SHOPPING_ADDRESS = "City Street 1";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @BeforeAll
    @SneakyThrows
    public static void setup(@Autowired DataSource dataSource,
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
                    new ClassPathResource(CLASS_PATH_RESOURCE_ADD)
            );
        }
    }

    @BeforeEach
    void setUp() {
        User john = new User();
        john.setId(USER_ID);
        john.setEmail(USER_EMAIL);
        john.setPassword(USER_PASSWORD);
        Role roleUser = new Role().setId(USER_ROLE_ID).setRoleName(Role.RoleName.USER);
        Role roleAdmin = new Role().setId(ADMIN_ROLE_ID).setRoleName(Role.RoleName.ADMIN);
        john.setRoles(Set.of(roleAdmin, roleUser));
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(john,
                john.getPassword(), john.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @AfterAll
    public static void afterAll(
            @Autowired DataSource dataSource
    ) {
        teardown(dataSource);
    }

    @Test
    @Transactional
    @WithMockUser(username = "user", roles = {"USER"})
    @DisplayName("Add cart item to user shopping cart")
    public void addToCart_WithCart_ShoppingCartDto() throws Exception {
        // Given
        Category category = new Category()
                .setId(3L);
        Book book = new Book()
                .setId(4L)
                .setCategories(Set.of(category));
        User user = new User()
                .setId(4L)
                .setEmail(USER_EMAIL)
                .setPassword(USER_PASSWORD)
                .setFirstName(USER_FIRST_NAME)
                .setLastName(USER_LAST_NAME)
                .setShippingAddress(USER_SHOPPING_ADDRESS);
        ShoppingCart shoppingCart = new ShoppingCart()
                .setId(2L)
                .setUser(user);
        CartItem cartItem = new CartItem()
                .setId(1L)
                .setBook(book)
                .setQuantity(2)
                .setShoppingCart(shoppingCart);
        shoppingCart.setCartItems(Set.of(cartItem));
        AddToCartRequestDto requestDto = new AddToCartRequestDto()
                .setBookId(book.getId())
                .setQuantity(2);
        ShoppingCartDto expectedShoppingCartDto = shoppingCartMapper.toDto(shoppingCart);
        String jsonBody = objectMapper.writeValueAsString(requestDto);

        // When
        MvcResult mvcResult = mockMvc.perform(post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        ShoppingCartDto actualShoppingCartDto = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );
        assertEquals(expectedShoppingCartDto, actualShoppingCartDto);
    }

    @SneakyThrows
    private static void teardown(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource(
                            CLASS_PATH_RESOURCE_DELETE)
            );
        }
    }
}
