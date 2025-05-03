package com.nocode.main.domain.model.dto.request.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UpdateCompany {
    @NotBlank(message = "El logo de la empresa es obligatorio.")
    private MultipartFile logo;

    @NotBlank(message = "El nombre de la empresa no puede estar vacío.")
    @Size(min = 3, max = 100, message = "El nombre de la empresa debe tener entre 3 y 100 caracteres.")
    private String name;

    @NotBlank(message = "La dirección de la empresa no puede estar vacía.")
    @Size(min = 5, max = 255, message = "La dirección de la empresa debe tener entre 5 y 255 caracteres.")
    private String address;

    @NotBlank(message = "El RUC de la empresa es obligatorio.")
    @Pattern(regexp = "^[0-9]{11}$", message = "El RUC debe ser un número de 11 dígitos.")
    private String ruc;

    @NotBlank(message = "El número de teléfono es obligatorio.")
    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe ser un número de 9 dígitos.")
    private String phone;
}
