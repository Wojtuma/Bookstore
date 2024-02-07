package org.springtask.bookstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springtask.bookstore.Entity.Category;
import org.springtask.bookstore.Repository.CategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new NoSuchElementException("Category with ID " + id + " does not exist");
        }
    }

    public Category getCategoryByName(String name) {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void updateCategory(Category category) {
        Category categoryToBeUpdated = getCategoryById(category.getId());

        if (categoryToBeUpdated != null) {
            categoryToBeUpdated.setName(category.getName());
        } else{
            throw new NoSuchElementException("Category with ID " + category.getId() + " does not exist");
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
