package org.springtask.bookstore.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookstore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private double cashAmount = 0.0;

    @OneToMany(mappedBy = "bookstore")
    private Set<Book> books = new HashSet<>();

    @OneToMany(mappedBy = "bookstore")
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "bookstore")
    private Set<Customer> customers = new HashSet<>();
}
