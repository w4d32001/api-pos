package com.nocode.main.application.mapper;

import com.nocode.main.domain.model.Supplier;
import com.nocode.main.domain.model.dto.SupplierDto;

public class SupplierDtoMapper {

    public static Supplier toDomain(SupplierDto dto) {
        return Supplier.builder()
                .id(dto.getId())
                .name(dto.getName())
                .documentNumber(dto.getDocumentNumber())
                .documentType(dto.getDocumentType())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public static SupplierDto toDto(Supplier domain) {
        return SupplierDto.builder()
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
