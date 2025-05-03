package com.nocode.main.application.service;

import com.nocode.main.application.mapper.CategoryDtoMapper;
import com.nocode.main.application.usecases.ICategoryService;
import com.nocode.main.domain.model.Category;
import com.nocode.main.domain.model.dto.CategoryDto;
import com.nocode.main.domain.model.dto.request.category.CreateCategoryRequest;
import com.nocode.main.domain.model.dto.request.category.UpdateCategoryRequest;
import com.nocode.main.domain.port.ICategoryPersistencePort;
import com.nocode.main.infrastructure.shared.exception.ConflictException;
import com.nocode.main.infrastructure.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryPersistencePort categoryPersistencePort;

    @Autowired
    public CategoryService(final ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(CreateCategoryRequest request) {
        categoryPersistencePort.findByName(request.getName())
                .ifPresent(n -> {
                    throw new ConflictException("La categoría con el nombre: '" + request.getName() + "' ya existe.");
                });

        Category category = Category.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        categoryPersistencePort.createCategory(category);
    }

    @Override
    public Page<CategoryDto> findAllCategories(String search, int page, int size) {

        Page<Category> categoryPage = categoryPersistencePort.findAllCategories(search, page, size);

        return categoryPage.map(CategoryDtoMapper::toDto);

    }

    @Override
    public void updateCategory(String id, UpdateCategoryRequest request) {
        Category category = categoryPersistencePort.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "id", id)
                );

        categoryPersistencePort.findByName(request.getName())
                .filter(n -> !n.getId().equals(id))
                .ifPresent(n -> {
                    throw new ConflictException("La categoría con el nombre: '" + request.getName() + "' ya existe.");
                });

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setUpdatedAt(LocalDateTime.now());

        categoryPersistencePort.updateCategory(category);

    }

    @Override
    public void deleteCategory(String id) {
        Category category = categoryPersistencePort.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "id", id)
                );

        categoryPersistencePort.deleteCategory(category.getId());
    }

    @Override
    public CategoryDto findById(String id) {
        Category category = categoryPersistencePort.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "id", id)
                );

        return CategoryDtoMapper.toDto(category);
    }

    @Override
    public CategoryDto findByName(String name) {
        Category category = categoryPersistencePort.findByName(name)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "name", name)
                );

        return CategoryDtoMapper.toDto(category);
    }
}
