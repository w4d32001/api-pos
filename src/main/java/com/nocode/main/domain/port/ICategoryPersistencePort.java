package com.nocode.main.domain.port;

import com.nocode.main.domain.model.Category;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ICategoryPersistencePort {

    void createCategory(Category category);

    Page<Category> findAllCategories(String search, int page, int size);

    void updateCategory(Category category);

    void deleteCategory(String id);

    Optional<Category> findByName(String name);

    Optional<Category> findById(String id);

}
