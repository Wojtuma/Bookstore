package org.springtask.bookstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springtask.bookstore.Entity.Book;
import org.springtask.bookstore.Repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(UUID id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new NoSuchElementException("Book with ID " + id + " does not exist");
        }
    }

    public Book getBookByTitle(String title) {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public void updateBook(Book book) {
        Book bookToBeUpdated = getBookById(book.getId());

        if (bookToBeUpdated != null) {
            bookToBeUpdated.setTitle(book.getTitle());
            bookToBeUpdated.setAuthor(book.getAuthor());
            bookToBeUpdated.setPublicationYear(book.getPublicationYear());
            bookToBeUpdated.setPrice(book.getPrice());
            bookToBeUpdated.setAvailableCopies(book.getAvailableCopies());
        } else{
            throw new NoSuchElementException("Book with ID " + book.getId() + " does not exist");
        }
        bookRepository.save(book);
    }

    public void sellBook(UUID id) {
        Book book = getBookById(id);
        int availableCopies = book.getAvailableCopies();
        if (availableCopies > 0) {
            availableCopies--;
            bookRepository.save(book);
            System.out.println("Book sold. Available copies: " + availableCopies);
        } else {
            System.out.println("Sorry, the book is out of stock.");
        }
    }
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
