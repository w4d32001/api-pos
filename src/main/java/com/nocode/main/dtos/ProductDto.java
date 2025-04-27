package com.nocode.main.dtos;

import com.nocode.main.entities.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductDto extends GenericDto {

    private String id;

    private String name;

    private List<CategoryDto> categories = new ArrayList<>();

    private int stock;

    private String description;

    private String image;

    private MultipartFile imageUrl;

    private String barCode;
}
