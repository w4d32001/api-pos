package com.nocode.main.application.mapper;

import com.nocode.main.domain.model.User;
import com.nocode.main.domain.model.dto.UserDto;

public class UserDtoMapper {

    public static User toDomain(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .documentType(dto.getDocumentType())
                .documentNumber(dto.getDocumentNumber())
                .address(dto.getAddress())
                .userName(dto.getUserName())
                .role(dto.getRole())
                .status(dto.isStatus())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    public static UserDto toDto(User domain) {
        return UserDto.builder()
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
