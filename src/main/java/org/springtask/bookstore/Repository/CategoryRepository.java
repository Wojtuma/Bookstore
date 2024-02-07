package org.springtask.bookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springtask.bookstore.Entity.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}

