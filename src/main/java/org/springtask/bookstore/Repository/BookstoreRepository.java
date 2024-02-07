package org.springtask.bookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springtask.bookstore.Entity.Bookstore;

import java.util.UUID;

public interface BookstoreRepository extends JpaRepository<Bookstore, UUID> {

}

