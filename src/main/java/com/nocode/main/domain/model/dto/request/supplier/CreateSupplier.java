package com.nocode.main.domain.model.dto.request.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSupplier {
    @NotBlank(message = "El nombre es obligatorio.")
    private String name;

    @NotBlank(message = "El tipo de documento es obligatorio.")
    private String documentType;

    @NotBlank(message = "El número de documento es obligatorio.")
    @Pattern(regexp = "^[0-9]+$", message = "El número de documento solo puede contener números.")
    private String documentNumber;

    @NotBlank(message = "La dirección es obligatoria.")
    private String address;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe contener exactamente 10 dígitos numéricos.")
    private String phone;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "El correo electrónico debe ser válido.")
    private String email;
}
