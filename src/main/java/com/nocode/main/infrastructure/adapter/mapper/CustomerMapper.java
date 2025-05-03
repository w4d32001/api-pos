package com.nocode.main.infrastructure.adapter.mapper;

import com.nocode.main.domain.model.Customer;
import com.nocode.main.infrastructure.adapter.entity.CustomerEntity;

public class CustomerMapper {

    public static Customer toDomain(CustomerEntity entity) {
        return Customer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .documentType(entity.getDocumentType())
                .documentNumber(entity.getDocumentNumber())
                .phone(entity.getPhone())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static CustomerEntity toEntity(Customer domain) {
        return CustomerEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .documentType(domain.getDocumentType())
                .documentNumber(domain.getDocumentNumber())
                .phone(domain.getPhone())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
