package com.nocode.main.services.customer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateRequest {
      @NotBlank(message = "El campo nombre es obligatorio")
      @Size(min = 3, max = 50, message = "El campo nombre debe tener entre 3 y 50 caracteres")
      private String name;

      @NotBlank(message = "El campo tipo de documento es obligatorio")
      @Size(min = 3, max = 50, message = "El campo tipo de documento debe tener entre 3 y 50 caracteres")
      private String documentType;

      @NotBlank(message = "El campo número de documento es obligatorio")
      @Size(min = 3, max = 50, message = "El campo número de documento debe tener entre 3 y 50 caracteres")
      private String documentNumber;

      @NotBlank(message = "El campo teléfono es obligatorio")
      @Size(min = 9, max = 9, message = "El campo teléfono debe tener 9 caracteres")
      private String phone;
}
