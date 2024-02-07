package org.springtask.bookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springtask.bookstore.Entity.Customer;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}

