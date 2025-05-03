package com.nocode.main.domain.model.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCategoryRequest {
    @NotBlank(message = "El nombre de la categoría no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre de la categoría debe tener entre 3 y 50 caracteres.")
    private String name;

    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres.")
    private String description;
}
