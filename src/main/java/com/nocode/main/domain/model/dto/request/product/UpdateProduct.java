package com.nocode.main.domain.model.dto.request.product;

import com.nocode.main.domain.model.dto.CategoryDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class UpdateProduct {
    @NotBlank(message = "El nombre del producto es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre del producto debe tener entre 3 y 100 caracteres.")
    private String name;

    private List<CategoryDto> categories;

    @NotBlank(message = "La descripción del producto es obligatoria.")
    @Size(min = 10, max = 500, message = "La descripción del producto debe tener entre 10 y 500 caracteres.")
    private String description;

    @NotNull(message = "La imagen es obligatoria.")
    private MultipartFile imageUrl;
}
