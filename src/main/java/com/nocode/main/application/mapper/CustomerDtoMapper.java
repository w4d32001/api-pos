package com.nocode.main.application.mapper;

import com.nocode.main.domain.model.Customer;
import com.nocode.main.domain.model.dto.CustomerDto;

public class CustomerDtoMapper {
    public static Customer toDomain(CustomerDto dto) {
        return Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .documentType(dto.getDocumentType())
                .documentNumber(dto.getDocumentNumber())
                .phone(dto.getPhone())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public static CustomerDto toDto(Customer domain) {
        return CustomerDto.builder()
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
