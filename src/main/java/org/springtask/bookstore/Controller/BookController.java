package org.springtask.bookstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springtask.bookstore.Entity.Book;
import org.springtask.bookstore.Service.BookService;
import org.springtask.bookstore.Service.BookstoreService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookstoreService bookstoreService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable UUID id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/title/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping(value = "/update/{id}")
    public String updateBook(@PathVariable UUID id, @RequestBody Book book) {
        bookService.updateBook(book);
        return "Updated!";
    }

    @PostMapping("/sell/{id}")
    public ResponseEntity<String> sellBook(@PathVariable UUID id) {
        try {
            Book book = bookService.getBookById(id);
            bookService.sellBook(id);
            // Call the sellBook method from BookstoreService
            bookstoreService.sellBook(book);

            // Additional logic or return a success message
            return ResponseEntity.ok("Book sold successfully.");
        } catch (Exception e) {
            // Handle exceptions or validation errors
            return ResponseEntity.badRequest().body("Failed to sell book: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
    }
}

