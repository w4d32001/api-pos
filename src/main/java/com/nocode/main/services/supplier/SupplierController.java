package com.nocode.main.services.supplier;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nocode.main.business.SupplierBusiness;
import com.nocode.main.commos.ApiResponse;
import com.nocode.main.commos.ResponseBuilder;
import com.nocode.main.dtos.SupplierDto;
import com.nocode.main.services.supplier.request.StoreRequest;
import com.nocode.main.services.supplier.request.UpdateRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/suppliers")
public class SupplierController {
      
    private SupplierBusiness _supplier;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SupplierDto>>> findAllSuppliers(
          @RequestParam(defaultValue = "") String search,
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "10") int size
    ){
          Page<SupplierDto> suppliers = _supplier.findAllSuppliers(search, page, size);
          return ResponseBuilder.ok("Lista de proveedores", suppliers);
    }

      @PostMapping
      public ResponseEntity<ApiResponse<SupplierDto>> createSupplier(@Valid @RequestBody StoreRequest request){
            SupplierDto dto = SupplierDto.builder()
                  .name(request.getName())
                  .documentType(request.getDocumentType())
                  .documentNumber(request.getDocumentNumber())
                  .phone(request.getPhone())
                  .email(request.getEmail())
                  .build();
            return ResponseBuilder.created("Proveedor creado exitosamente", _supplier.createSupplier(dto));
      }

      @PutMapping("/{id}")
      public ResponseEntity<ApiResponse<SupplierDto>> updateSupplier(@PathVariable String id, @Valid @RequestBody UpdateRequest request){
            SupplierDto dto = SupplierDto.builder()
                  .name(request.getName())
                  .documentType(request.getDocumentType())
                  .documentNumber(request.getDocumentNumber())
                  .phone(request.getPhone())
                  .email(request.getEmail())
                  .build();
            return ResponseBuilder.ok("Proveedor actualizado exitosamente", _supplier.updateSupplier(id, dto));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<ApiResponse<Void>> deleteSupplier(@PathVariable String id){
            _supplier.deleteSupplier(id);
            return ResponseBuilder.deleted("Proveedor eliminado exitosamente");
      }


}
