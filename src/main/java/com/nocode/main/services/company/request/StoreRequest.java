package com.nocode.main.services.company.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class StoreRequest {
      @NotBlank(message = "El campo nombre no puede ser nulo")
      private MultipartFile logo;

      @NotBlank(message = "El campo nombre no puede ser nulo")
      private String name;

      @NotBlank(message = "El campo nombre no puede ser nulo")
      private String address;

      @NotBlank(message = "El campo nombre no puede ser nulo")
      private String ruc;

      @NotBlank(message = "El campo nombre no puede ser nulo")
      private String phone;
}
