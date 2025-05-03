package com.nocode.main.application.mapper;

import com.nocode.main.domain.model.Company;
import com.nocode.main.domain.model.dto.CompanyDto;

public class CompanyDtoMapper {

    public static Company toDomain(CompanyDto dto) {
        return Company.builder()
                .id(dto.getId())
                .name(dto.getName())
                .ruc(dto.getRuc())
                .phone(dto.getPhone())
                .logo(dto.getLogo())
                .address(dto.getAddress())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public static CompanyDto toDto(Company domain) {
        return CompanyDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .ruc(domain.getRuc())
                .phone(domain.getPhone())
                .logo(domain.getLogo())
                .address(domain.getAddress())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
