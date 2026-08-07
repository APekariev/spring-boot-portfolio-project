package com.example.springbootproject;

import com.example.springbootproject.model.Book;
import com.example.springbootproject.service.BookService;
import java.math.BigDecimal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootProjectApplication {
    public static void main(String[] args) {
        Book treasureIsland = new Book();
        treasureIsland.setTitle("The treasure island");
        treasureIsland.setAuthor("Jul Vern");
        treasureIsland.setDescription("adventurous novel");
        treasureIsland.setIsbn("978-0-14-044913-6");
        treasureIsland.setPrice(BigDecimal.valueOf(400));
        Book gospozhaBovari = new Book();
        gospozhaBovari.setTitle("Gospozha Bovari");
        gospozhaBovari.setAuthor("Gustave Flaubert");
        gospozhaBovari.setDescription("drama");
        gospozhaBovari.setIsbn("993-0-18-044960-6");
        gospozhaBovari.setPrice(BigDecimal.valueOf(300));
        ConfigurableApplicationContext context = SpringApplication
                .run(SpringBootProjectApplication.class, args);
        BookService bookService = context.getBean(BookService.class);
        bookService.save(treasureIsland);
        bookService.save(gospozhaBovari);
        System.out.println(bookService.findAll());
    }
}
