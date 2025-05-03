package com.nocode.main.domain.model.dto.request.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCustomer {
    @NotBlank(message = "El nombre del cliente es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre del cliente debe tener entre 3 y 100 caracteres.")
    private String name;

    @NotBlank(message = "El tipo de documento es obligatorio.")
    private String documentType;

    @NotBlank(message = "El número de documento es obligatorio.")
    @Pattern(regexp = "^[0-9]{8,15}$", message = "El número de documento debe ser un número válido entre 8 y 15 dígitos.")
    private String documentNumber;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe ser un número de 9 dígitos.")
    private String phone;
}
