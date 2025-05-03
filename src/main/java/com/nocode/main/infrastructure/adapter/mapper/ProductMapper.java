package com.nocode.main.infrastructure.adapter.mapper;

import com.nocode.main.domain.model.Category;
import com.nocode.main.domain.model.Product;
import com.nocode.main.infrastructure.adapter.entity.CategoryEntity;
import com.nocode.main.infrastructure.adapter.entity.ProductEntity;

import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .name(entity.getName())
                .stock(entity.getStock())
                .barCode(entity.getBarCode())
                .categories(entity.getCategories()
                        .stream()
                        .map(CategoryMapper::toDomain)
                        .collect(Collectors.toList()))
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static ProductEntity toEntity(Product domain) {
        return ProductEntity.builder()
                .id(domain.getId())
                .image(domain.getImage())
                .name(domain.getName())
                .stock(domain.getStock())
                .barCode(domain.getBarCode())
                .categories(domain.getCategories()
                        .stream()
                        .map(CategoryMapper::toEntity)
                        .collect(Collectors.toList()))
                .description(domain.getDescription())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
