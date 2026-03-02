package dev.jonkursani.restapigr1.controllers;

import dev.jonkursani.restapigr1.entities.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private List<Book> books = new ArrayList<>();

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("Title one", "Author one", "science"),
                new Book("Title two", "Author two", "history"),
                new Book("Title three", "Author three", "math")
        ));
    }

    public BookController() {
        initializeBooks();
    }

    // R => Read
    // @QueryParameters
    @GetMapping("/api/books") // ?category=science
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    // @PathVariable
//    @GetMapping("/api/books/{id}")
//    public Book getBookById(@PathVariable int id) {
//        return books.get(id);
//    }

    @GetMapping("/api/books/{title}")
    public Book getBookByTitle(@PathVariable String title) {
//        for (Book book : books) {
//            if (book.getTitle().equalsIgnoreCase(title)) {
//                return book;
//            }
//        }
//
//        return null;

        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}