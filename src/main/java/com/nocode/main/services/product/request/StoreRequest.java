package com.nocode.main.services.product.request;

import com.nocode.main.dtos.CategoryDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class StoreRequest {

    @NotNull(message = "El campo imagen no puede estar vacio")
    private MultipartFile imageUrl;
    @NotNull(message = "El campo nombre no puede estar vacio")
    private String name;
    @NotNull(message = "El campo stock no puede estar vacio")
    private int stock;
    @Size(min = 4, message = "El capo descrición debe tener mas de 4 caracteres")
    private String description;
    @NotNull(message = "Debe haber al menos una categoria")
    private List<CategoryDto> categories;

}

