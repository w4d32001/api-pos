package com.nocode.main.infrastructure.adapter.mapper;

import com.nocode.main.domain.model.Supplier;
import com.nocode.main.infrastructure.adapter.entity.SupplierEntity;

public class SupplierMapper {

    public static Supplier toDomain(SupplierEntity entity) {
        return Supplier.builder()
                .id(entity.getId())
                .name(entity.getName())
                .documentNumber(entity.getDocumentNumber())
                .documentType(entity.getDocumentType())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static SupplierEntity toEntity(Supplier domain) {
        return SupplierEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .documentNumber(domain.getDocumentNumber())
                .documentType(domain.getDocumentType())
                .phone(domain.getPhone())
                .email(domain.getEmail())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
