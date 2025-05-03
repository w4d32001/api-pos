package com.nocode.main.infrastructure.controllers;

import com.nocode.main.application.service.CompanyService;
import com.nocode.main.domain.model.dto.CompanyDto;
import com.nocode.main.domain.model.dto.request.company.CreateCompany;
import com.nocode.main.domain.model.dto.request.company.UpdateCompany;
import com.nocode.main.infrastructure.shared.response.ApiResponse;
import com.nocode.main.infrastructure.shared.response.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CompanyDto>>> findAllCompanies(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CompanyDto> companyDtos = companyService.findAllCompanies(search, page, size);

        return ResponseBuilder.ok("Lista de empresas", companyDtos);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> saveCompany(@Valid @ModelAttribute CreateCompany request) {
        companyService.saveCompany(request);
        return ResponseBuilder.created("Empresa creada exitosamente");
    }

    @PostMapping(path = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> updateCompany(@PathVariable String id, @Valid @ModelAttribute UpdateCompany request) {
        companyService.updateCompany(id, request);
        return ResponseBuilder.ok("Empresa actualizada exitosamente");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(@PathVariable String id) {
        companyService.deleteCompany(id);
        return ResponseBuilder.deleted("Compañia eliminada exitosamente");
    }

}
