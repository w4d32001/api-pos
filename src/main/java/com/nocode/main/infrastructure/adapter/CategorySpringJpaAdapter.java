package com.nocode.main.infrastructure.adapter;

import com.nocode.main.domain.model.Category;
import com.nocode.main.domain.port.ICategoryPersistencePort;
import com.nocode.main.infrastructure.adapter.entity.CategoryEntity;
import com.nocode.main.infrastructure.adapter.mapper.CategoryMapper;
import com.nocode.main.infrastructure.adapter.repository.ICategorsRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CategorySpringJpaAdapter implements ICategoryPersistencePort {

    private final ICategorsRepository categoryRepository;

    public CategorySpringJpaAdapter(ICategorsRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(Category category) {

        CategoryEntity entity = CategoryMapper.toEntity(category);
        categoryRepository.save(entity);

    }

    @Override
    public Page<Category> findAllCategories(String search, int page, int size) {
        Pageable pageableCategory = PageRequest.of(page, size);
        Page<CategoryEntity> categoryPage = categoryRepository.findByNameContainingIgnoreCase(search, pageableCategory);

        return categoryPage.map(CategoryMapper::toDomain);
    }

    @Override
    public void updateCategory(Category category) {
        CategoryEntity entity = CategoryMapper.toEntity(category);
        categoryRepository.save(entity);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name)
                .map(CategoryMapper::toDomain);
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::toDomain);
    }
}
