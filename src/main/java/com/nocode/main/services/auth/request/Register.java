package com.nocode.main.services.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Register {
      @Null
      private String name;
      @Null
      private String email;
      @NotBlank(message = "El campo contraseña es obligatorio")
      private String password;
      @Null
      private String phone;
      @Null
      private String documentType;
      @Null
      private String documentNumber;
      @Null
      private String address;
      @NotBlank(message = "El campo nombre de usuario es obligatorio")
      private String userName;
}
