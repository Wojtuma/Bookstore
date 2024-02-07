package org.springtask.bookstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springtask.bookstore.Entity.Book;
import org.springtask.bookstore.Entity.Bookstore;
import org.springtask.bookstore.Service.BookService;
import org.springtask.bookstore.Service.BookstoreService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore")
@CrossOrigin("*")
public class BookstoreController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @PostMapping("/create")
    public void createBookstore(@RequestBody Bookstore bookstore) {
        bookstoreService.saveBookstore(bookstore);
    }

    @GetMapping
    public List<Bookstore> getAllBookstores() {
        return bookstoreService.getAllBookstores();
    }

    @PutMapping(value = "/update/{id}")
    public String updateBookstore(@PathVariable UUID id, @RequestBody Bookstore bookstore) {
        bookstoreService.updateBookstore(bookstore);
        return "Updated!";
    }


}
