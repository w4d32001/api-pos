package com.nocode.main.business;

import com.nocode.main.dtos.CategoryDto;
import com.nocode.main.entities.CategoryEntity;
import com.nocode.main.exception.ConflictException;
import com.nocode.main.exception.ResourceNotFoundException;
import com.nocode.main.repositories.ICategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CategoryBusiness {

    @Autowired
    private ICategoryRepository _repo;

    public Page<CategoryDto> findAllCategories(String search, int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        Page<CategoryEntity> categoryEntityPage = _repo.findByNameContainingIgnoreCase(search, pageable);

        return categoryEntityPage.map(
                categoryEntity -> {
                    CategoryDto dto = new CategoryDto();
                    dto.setId(categoryEntity.getId());
                    dto.setName(categoryEntity.getName());
                    dto.setDescription(categoryEntity.getDescription());
                    dto.setCreatedAt(categoryEntity.getCreatedAt());
                    dto.setUpdatedAt(categoryEntity.getUpdatedAt());
                    return  dto;
                }
        );

    }

    @Transactional
    public CategoryDto createCategory(CategoryDto dto){

        _repo.findByName(dto.getName())
                .ifPresent( n -> {
                    throw  new ConflictException("La categoría con el nombre: '" + dto.getName() + "' ya existe.");
                } );

        CategoryEntity entity = CategoryEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(dto.getName())
                .description(dto.getDescription() == null || dto.getDescription().isEmpty()
                        ? "Sin descripción"
                        : dto.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        CategoryEntity saved = _repo.save(entity);

        return  CategoryDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();

    }

    @Transactional
    public CategoryDto updateCategory(String id, CategoryDto dto){

        CategoryEntity existing = _repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria", "id", id)
        );

        _repo.findByName(dto.getName())
                .filter( n -> !n.getId().equals(id) )
                .ifPresent( n -> {
                    throw  new ConflictException("La categoría con el nombre: '" + dto.getName() + "' ya existe.");
                } );

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setUpdatedAt(LocalDateTime.now());

        _repo.save(existing);

        return CategoryDto.builder()
                .id(existing.getId())
                .name(existing.getName())
                .description(existing.getDescription())
                .createdAt(existing.getCreatedAt())
                .updatedAt(existing.getUpdatedAt())
                .build();

    }

    @Transactional
    public void deleteCategory(String id){

        CategoryEntity existing = _repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria", "id", id)
        );

        _repo.deleteById(existing.getId());

    }

}
