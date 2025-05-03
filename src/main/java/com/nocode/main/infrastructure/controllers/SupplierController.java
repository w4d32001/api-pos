package com.nocode.main.infrastructure.controllers;

import com.cloudinary.Api;
import com.nocode.main.application.service.SupplierService;
import com.nocode.main.domain.model.dto.SupplierDto;
import com.nocode.main.domain.model.dto.request.supplier.CreateSupplier;
import com.nocode.main.domain.model.dto.request.supplier.UpdateSupplier;
import com.nocode.main.infrastructure.shared.response.ApiResponse;
import com.nocode.main.infrastructure.shared.response.ResponseBuilder;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/suppliers")
@RestController
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(final SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SupplierDto>>> findAllSuppliers(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<SupplierDto> supplierDtos = supplierService.findAllSuppliers(search, page, size);
        return ResponseBuilder.ok("Lista de proveedores", supplierDtos);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveSupplier(@Valid @RequestBody CreateSupplier request) {
        supplierService.saveSupplier(request);
        return ResponseBuilder.created("Proveedor creado exitosamente");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Void>> updateSupplier(@PathVariable String id, @Valid @RequestBody UpdateSupplier request) {
        supplierService.updateSupplier(id, request);
        return ResponseBuilder.ok("Proveedor actualizado exitosamente");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSupplier(@PathVariable String id) {
        supplierService.deleteSupplier(id);
        return ResponseBuilder.deleted("Proveedor eliminado exitosamente");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<SupplierDto>> findById(@PathVariable String id) {
        SupplierDto supplierDto = supplierService.findById(id);
        return ResponseBuilder.ok("Detalle del proveedor", supplierDto);
    }

    @GetMapping(path = "/phone/{phone}")
    public ResponseEntity<ApiResponse<SupplierDto>> findByPhone(@PathVariable String phone) {
        SupplierDto supplierDto = supplierService.findByPhone(phone);
        return ResponseBuilder.ok("Detalle del proveedor", supplierDto);
    }

    @GetMapping(path = "/documentNumber/{documentNumber}")
    public ResponseEntity<ApiResponse<SupplierDto>> findByDocumentNumber(@PathVariable String documentNumber) {
        SupplierDto supplierDto = supplierService.findByDocumentNumber(documentNumber);
        return ResponseBuilder.ok("Detalle del proveedor", supplierDto);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<ApiResponse<SupplierDto>> findByEmail(@PathVariable String email) {
        SupplierDto supplierDto = supplierService.findByEmail(email);
        return ResponseBuilder.ok("Detalle del proveedor", supplierDto);
    }

}
