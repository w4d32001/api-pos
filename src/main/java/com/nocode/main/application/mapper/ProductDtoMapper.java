package com.nocode.main.application.mapper;

import com.nocode.main.domain.model.Product;
import com.nocode.main.domain.model.dto.ProductDto;
import com.nocode.main.infrastructure.adapter.mapper.CategoryMapper;

import java.util.stream.Collectors;

public class ProductDtoMapper {

    public static Product toDomain(ProductDto dto) {
        return Product.builder()
                .id(dto.getId())
                .image(dto.getImage())
                .name(dto.getName())
                .stock(dto.getStock())
                .barCode(dto.getBarCode())
                .categories(dto.getCategories()
                        .stream()
                        .map(CategoryDtoMapper::toDomain)
                        .collect(Collectors.toList()))
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public static ProductDto toDto(Product domain) {
        return ProductDto.builder()
                .id(domain.getId())
                .image(domain.getImage())
                .name(domain.getName())
                .stock(domain.getStock())
                .barCode(domain.getBarCode())
                .categories(domain.getCategories()
                        .stream()
                        .map(CategoryDtoMapper::toDto)
                        .collect(Collectors.toList()))
                .description(domain.getDescription())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
