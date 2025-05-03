package com.nocode.main.infrastructure.adapter.mapper;

import com.nocode.main.domain.model.User;
import com.nocode.main.infrastructure.adapter.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .documentType(entity.getDocumentType())
                .documentNumber(entity.getDocumentNumber())
                .address(entity.getAddress())
                .userName(entity.getUserName())
                .role(entity.getRole())
                .status(entity.isStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static UserEntity toEntity(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .email(domain.getEmail())
                .phone(domain.getPhone())
                .documentType(domain.getDocumentType())
                .documentNumber(domain.getDocumentNumber())
                .address(domain.getAddress())
                .userName(domain.getUserName())
                .role(domain.getRole())
                .status(domain.isStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
