package com.nocode.main.infrastructure.adapter.mapper;

import com.nocode.main.domain.model.Company;
import com.nocode.main.infrastructure.adapter.entity.CompanyEntity;

public class CompanyMapper {

    public static Company toDomain(CompanyEntity entity) {
        return Company.builder()
                .id(entity.getId())
                .name(entity.getName())
                .ruc(entity.getRuc())
                .phone(entity.getPhone())
                .logo(entity.getLogo())
                .address(entity.getAddress())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static CompanyEntity toEntity(Company domain) {
        return CompanyEntity.builder()
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
