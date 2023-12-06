package mate.academy.bookshop;

import java.math.BigDecimal;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookShopApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book optimizingJava = new Book();
            optimizingJava.setTitle("Optimizing Java");
            optimizingJava.setAuthor("Benjamin J Evans");
            optimizingJava.setPrice(BigDecimal.valueOf(3450));
            optimizingJava.setIsbn("978-1492025795");
            optimizingJava.setDescription(
                    "This practical guide focus on the practicalities "
                            + "of Java application performance tuning.");
            optimizingJava.setCoverImage(
                    "https://balka-book.com/files/2023/09_13/11_28/u_files_store_5_7.jpg");

            Book coreJava = new Book();
            coreJava.setTitle("Core Java for the Impatient");
            coreJava.setAuthor("Cay S. Horstmann");
            coreJava.setPrice(BigDecimal.valueOf(3480));
            coreJava.setIsbn("978-0138052102");
            coreJava.setDescription(
                    "It is a complete yet concise guide "
                            + "that reflects all changes through Java SE 17, "
                            + "Oracle's latest Long-Term Support (LTS) release.");
            coreJava.setCoverImage(
                    "https://balka-book.com/files/2023/07_04/09_50/u_files_store_5_7.jpg");

            bookService.save(optimizingJava);
            bookService.save(coreJava);

            System.out.println(bookService.findAll());
        };
    }
}
