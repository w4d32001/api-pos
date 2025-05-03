package com.nocode.main.application.mapper;

import com.nocode.main.domain.model.Category;
import com.nocode.main.domain.model.dto.CategoryDto;

public class CategoryDtoMapper {

    public static Category toDomain(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public static CategoryDto toDto(Category domain) {
        return CategoryDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
