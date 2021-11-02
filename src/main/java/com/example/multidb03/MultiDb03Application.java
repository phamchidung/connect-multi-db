package com.example.multidb03;

import com.example.multidb03.book.repository.BookRepository;
import com.example.multidb03.model.book.Book;
import com.example.multidb03.model.user.User;
import com.example.multidb03.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class MultiDb03Application {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void addData2DB() {
        userRepository
                .saveAll(
                        Stream.of(
                                new User(744, "john"),
                                new User(455, "Bird")
                        ).collect(Collectors.toList()));

        bookRepository
                .saveAll(
                        Stream.of(
                                new Book(111, " Big dick"),
                                new Book(112, "Lover")
                        ).collect(Collectors.toList())
                );
    }

    @GetMapping("users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(MultiDb03Application.class, args);
    }

}
