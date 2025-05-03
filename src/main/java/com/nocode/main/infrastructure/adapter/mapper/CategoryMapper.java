package com.nocode.main.infrastructure.adapter.mapper;

import com.nocode.main.domain.model.Category;
import com.nocode.main.infrastructure.adapter.entity.CategoryEntity;

public class CategoryMapper {

    public static Category toDomain(CategoryEntity entity) {
        return Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static CategoryEntity toEntity(Category domain) {
        return CategoryEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
