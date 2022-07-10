package com.company;

import com.company.model.Book;
import com.company.repository.BookRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.util.Arrays;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Bookstore service application",
                description = "Bookstore crud services",
                version = "v1"
        )
)
public class BookStoreApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookStoreApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Book book1 = Book.builder()
                .name("Martin Iden")
                .author("Jack London")
                .price(10.0)
                .stock(10).build();
        Book book2 = Book.builder()
                .name("Taras Bulba")
                .author("Nikolay Vasiliyevic Gogol")
                .price(11.1)
                .stock(10).build();
        Book book3 = Book.builder()
                .name("Sicanlar ve Adamlar")
                .author("Jerome David Salingeer")
                .price(15.5)
                .stock(10).build();
        bookRepository.saveAll(Arrays.asList(book1, book2, book3));
   }
}
