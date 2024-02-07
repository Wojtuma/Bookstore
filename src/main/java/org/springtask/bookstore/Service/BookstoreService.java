package org.springtask.bookstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springtask.bookstore.Entity.Book;
import org.springtask.bookstore.Entity.Bookstore;
import org.springtask.bookstore.Repository.BookstoreRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookstoreService {
    @Autowired
    private BookstoreRepository bookstoreRepository;

    public String saveBookstore(Bookstore bookstore) {
        Bookstore bookstoreToBeChecked = getBookstoreByName(bookstore.getName());
        if (bookstoreToBeChecked == null) {
            bookstoreRepository.save(bookstore);
            return "Bookstore saved successfully";
        } else {
            return "Bookstore with name: \"" + bookstoreToBeChecked.getName() + "\" already exists";
        }
    }

    public List<Bookstore> getAllBookstores() {
        return bookstoreRepository.findAll();
    }

    public Bookstore getBookstoreById(UUID id) {
        Optional<Bookstore> optionalBookstore = bookstoreRepository.findById(id);
        if (optionalBookstore.isPresent()) {
            return optionalBookstore.get();
        } else {
            throw new NoSuchElementException("Bookstore with ID " + id + " does not exist");
        }
    }

    public Bookstore getBookstoreByName(String name) {
        List<Bookstore> bookstoreList = bookstoreRepository.findAll();
        return bookstoreList.stream()
                .filter(bookstore -> bookstore.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void sellBook(Book book) {
            Bookstore bookstore = book.getBookstore();
            int availableCopies = book.getAvailableCopies();

            if (availableCopies > 0) {
                bookstore.setCashAmount(bookstore.getCashAmount() + book.getPrice());
                bookstoreRepository.save(bookstore);
                System.out.println("Book sold. Available copies: " + availableCopies);
            } else {
                System.out.println("Sorry, the book is out of stock.");
            }

    }

    public void updateBookstore(Bookstore bookstore) {
        Bookstore bookstoreToBeUpdated = getBookstoreById(bookstore.getId());

        if (bookstoreToBeUpdated != null) {
            bookstoreToBeUpdated.setName(bookstore.getName());
            bookstoreToBeUpdated.setCashAmount(bookstore.getCashAmount());
        } else{
            throw new NoSuchElementException("Bookstore with ID " + bookstore.getId() + " does not exist");
        }
        bookstoreRepository.save(bookstore);
    }
}
