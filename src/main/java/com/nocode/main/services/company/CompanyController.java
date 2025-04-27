package com.nocode.main.services.company;

import org.springframework.beans.factory.annotation.Autowired;
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

      @Autowired
      private CompanyBusiness _company;

      @GetMapping
      public ResponseEntity<ApiResponse<Page<CompanyDto>>> findAllCompanies(
                  @RequestParam(defaultValue = "") String search,
                  @RequestParam(defaultValue = "0") int page,
                  @RequestParam(defaultValue = "10") int size) {
            Page<CompanyDto> companies = _company.findAll(search, page, size);
            return ResponseBuilder.ok("Lista de empresas", companies);
      }

      @PostMapping(consumes = "multipart/form-data")
      public ResponseEntity<ApiResponse<Void>> createCompany(@Valid @ModelAttribute StoreRequest request) {
            CompanyDto dto = CompanyDto.builder()
                        .logo(request.getLogo())
                        .name(request.getName())
                        .address(request.getAddress())
                        .phone(request.getPhone())
                        .ruc(request.getRuc())
                        .build();

            _company.createCompany(dto);

            return ResponseBuilder.created("Empresa creada exitosamente");
      }

      @PostMapping(path = "/{id}", consumes = "multipart/form-data")
      public ResponseEntity<ApiResponse<Void>> updateCompany(@RequestParam String id,
                  @Valid @ModelAttribute UpdateRequest request) {
            CompanyDto dto = CompanyDto.builder()
                        .logo(request.getLogo())
                        .name(request.getName())
                        .address(request.getAddress())
                        .phone(request.getPhone())
                        .ruc(request.getRuc())
                        .build();

            _company.updateCompany(id, dto);

            return ResponseBuilder.ok("Empresa actualizada exitosamente");
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<ApiResponse<Void>> deleteCompany(@RequestParam String id) {
            _company.deleteCompany(id);
            return ResponseBuilder.deleted("Empresa eliminada exitosamente");
      }

}
