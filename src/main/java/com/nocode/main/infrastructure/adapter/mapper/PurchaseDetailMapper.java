package com.nocode.main.infrastructure.adapter.mapper;

import com.nocode.main.domain.model.PurchaseDetail;
import com.nocode.main.infrastructure.adapter.entity.PurchaseDetailEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseDetailMapper {

    public static PurchaseDetail toDomain(PurchaseDetailEntity entity) {
        return PurchaseDetail.builder()
                .id(entity.getId())
                .shopping(ShoppingMapper.toDomain(entity.getShopping()))
                .product(ProductMapper.toDomain(entity.getProduct()))
                .amount(entity.getAmount())
                .purchasePrice(entity.getPurchasePrice())
                .salePrice(entity.getSalePrice())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static PurchaseDetailEntity toEntity(PurchaseDetail domain) {
        return PurchaseDetailEntity.builder()
                .id(domain.getId())
                .shopping(ShoppingMapper.toEntity(domain.getShopping()))
                .product(ProductMapper.toEntity(domain.getProduct()))
                .amount(domain.getAmount())
                .purchasePrice(domain.getPurchasePrice())
                .salePrice(domain.getSalePrice())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public static List<PurchaseDetail> toDomainList(List<PurchaseDetailEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(entity -> toDomain((PurchaseDetailEntity) entity))
                .collect(Collectors.toList());
    }

    public static List<PurchaseDetailEntity> toEntityList(List<PurchaseDetail> domains) {
        if (domains == null) {
            return Collections.emptyList();
        }
        return domains.stream()
                .map(domain -> toEntity(domain))
                .collect(Collectors.toList());
    }
}
