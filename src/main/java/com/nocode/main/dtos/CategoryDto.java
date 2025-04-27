package com.nocode.main.dtos;

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
public class CategoryDto extends GenericDto {
    private String id;

    private String name;

    private String description;

    private List<ProductDto> products = new ArrayList<>();
}
