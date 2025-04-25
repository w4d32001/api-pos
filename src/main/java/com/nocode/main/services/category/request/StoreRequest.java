package com.nocode.main.services.category.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreRequest {

    @NotBlank(message = "El campo nombre no puede ser nulo")
    @Size(min = 2, message = "El campo nombre debe tener al menos 2 caracteres")
    private String name;

    @Size(min = 4, message = "El campo descripción debe tener al menos 4 caracteres")
    private String description;

}
