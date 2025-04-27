package com.nocode.main.business.product.dto;

import com.nocode.main.dtos.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ResponseProductDto {

    private String id;

    private String name;

    private List<CategoryDto> categories = new ArrayList<>();

    private int stock;

    private String description;

    private String image;

    private MultipartFile imageUrl;

    private String barCode;

    private LocalDateTime updatedAt;

}
