package com.nocode.main.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductDto extends GenericDto {
    private String name;

    private List<CategoryDto> categories = new ArrayList<>();

    private int stock;

    private String description;

    private String image;

    private String barCode;
}
