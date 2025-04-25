package com.nocode.main.services.company;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nocode.main.business.CompanyBusiness;
import com.nocode.main.commos.ApiResponse;
import com.nocode.main.commos.ResponseBuilder;
import com.nocode.main.dtos.CompanyDto;
import com.nocode.main.services.company.request.StoreRequest;
import com.nocode.main.services.company.request.UpdateRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
      
      private CompanyBusiness _company;

      @GetMapping
      public ResponseEntity<ApiResponse<Page<CompanyDto>>> findAllCompanies(
           @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
      ){
            Page<CompanyDto> companies = _company.findAll(search, page, size);
            return ResponseBuilder.ok("Lista de empresas", companies);
      }

      @PostMapping(consumes = "multipart/form-data")
      public ResponseEntity<ApiResponse<CompanyDto>> createCompany(@Valid @ModelAttribute StoreRequest request){
            CompanyDto dto = CompanyDto.builder()
                  .logo(request.getLogo())
                  .name(request.getName())
                  .address(request.getAddress())
                  .phone(request.getPhone())
                  .ruc(request.getRuc())
                  .build();
            return ResponseBuilder.created("Empresa creada exitosamente", _company.createCompany(dto));
      }

      @PostMapping(path = "/{id}", consumes = "multipart/form-data")
      public ResponseEntity<ApiResponse<CompanyDto>> updateCompany(@RequestParam String id, @Valid @ModelAttribute UpdateRequest request){
            CompanyDto dto = CompanyDto.builder()
                  .logo(request.getLogo())
                  .name(request.getName())
                  .address(request.getAddress())
                  .phone(request.getPhone())
                  .ruc(request.getRuc())
                  .build();
            return ResponseBuilder.ok("Empresa actualizada exitosamente", _company.updateCompany(id, dto));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<ApiResponse<Void>> deleteCompany(@RequestParam String id){
            _company.deleteCompany(id);
            return ResponseBuilder.deleted("Empresa eliminada exitosamente");
      }

}
