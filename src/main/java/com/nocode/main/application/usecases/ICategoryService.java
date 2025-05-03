package com.nocode.main.application.usecases;

import com.nocode.main.domain.model.Category;
import com.nocode.main.domain.model.dto.CategoryDto;
import com.nocode.main.domain.model.dto.request.category.CreateCategoryRequest;
import com.nocode.main.domain.model.dto.request.category.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    void saveCategory(CreateCategoryRequest request);

    Page<CategoryDto> findAllCategories(String search, int page, int size);

    void updateCategory(String id, UpdateCategoryRequest request);

    void deleteCategory(String id);

    CategoryDto findById(String id);

    CategoryDto findByName(String name);
}
