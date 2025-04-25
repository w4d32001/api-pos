package com.nocode.main.services.supplier.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreRequest {

      @NotBlank(message = "El campo nombre es obligatorio")
      @Size(min = 3, max = 250, message = "El campo nombre debe tener entre 3 y 250 caracteres")
      private String name;

      @NotBlank(message = "El campo tipo de documento es obligatorio")
      @Size(min = 3, max = 250, message = "El campo tipo de documento debe tener entre 3 y 250 caracteres")
      private String documentType;
  
      @NotBlank(message = "El campo número de documento es obligatorio")
      @Size(min = 3, max = 250, message = "El campo número de documento debe tener entre 3 y 250 caracteres")
      private String documentNumber;
  
      @Null
      @Size(min = 4, message = "El campo descripción debe tener al menos 4 caracteres")
      private String address;
  
      @NotBlank(message = "El campo teléfono es obligatorio")
      @Size(min = 9, max = 9, message = "El campo teléfono debe tener 9 caracteres")
      private String phone;
  
      @Null
      @Email(message = "El campo email debe ser un correo electrónico válido")
      private String email;
}
