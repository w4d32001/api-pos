package com.nocode.main.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nocode.main.business.AuthBusiness;
import com.nocode.main.commos.ApiResponse;
import com.nocode.main.commos.ResponseBuilder;
import com.nocode.main.dtos.AuthDto;
import com.nocode.main.dtos.UserDto;
import com.nocode.main.services.auth.request.Login;
import com.nocode.main.services.auth.request.Register;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {
      
      @Autowired
      private AuthBusiness _auth;

      @PostMapping("register")
      public ResponseEntity<ApiResponse<UserDto>> register(@Valid @RequestBody Register register){
            UserDto dto = UserDto.builder()
                  /* .name(register.getName())
                  .email(register.getEmail())
                  
                  .phone(register.getPhone())
                  .documentType(register.getDocumentType())
                  .documentNumber(register.getDocumentNumber())
                  .address(register.getAddress())*/
                  .password(register.getPassword())
                  .userName(register.getUserName())
                  .build();
            return ResponseBuilder.created("Usuario registrado exitosamente", _auth.registerUser(dto));
      }

      @PostMapping("login")
      public ResponseEntity<ApiResponse<AuthDto>> login(@RequestBody Login login){
            AuthDto dto = _auth.login(login.getUserName(), login.getPassword());
            return ResponseBuilder.ok("Usuario logueado exitosamente", dto);
      }

}
