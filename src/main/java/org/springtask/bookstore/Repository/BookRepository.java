package org.springtask.bookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springtask.bookstore.Entity.Book;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}

