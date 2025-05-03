package com.nocode.main.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class GenericDto {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
