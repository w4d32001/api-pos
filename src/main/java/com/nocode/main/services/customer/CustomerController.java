package com.nocode.main.services.customer;

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

import com.nocode.main.business.CustomerBusiness;
import com.nocode.main.commos.ApiResponse;
import com.nocode.main.commos.ResponseBuilder;
import com.nocode.main.dtos.CustomerDto;
import com.nocode.main.services.customer.request.StoreRequest;
import com.nocode.main.services.customer.request.UpdateRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
      
      private CustomerBusiness _customer;

      @GetMapping
      public ResponseEntity<ApiResponse<Page<CustomerDto>>> findAllCustomers(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
      ){
            Page<CustomerDto> customers = _customer.findAllCustomers(search, page, size);
            return ResponseBuilder.ok("Lista de clientes", customers);
      }

      @PostMapping
      public ResponseEntity<ApiResponse<CustomerDto>> createCustomer(@Valid @RequestBody StoreRequest request){
            CustomerDto dto = CustomerDto.builder()
                  .name(request.getName())
                  .documentType(request.getDocumentType())
                  .documentNumber(request.getDocumentNumber())
                  .phone(request.getPhone())
                  .build();
            return ResponseBuilder.created("Cliente creado exitosamente", _customer.createCustomer(dto));
      }

      @PutMapping("/{id}")
      public ResponseEntity<ApiResponse<CustomerDto>> updateCustomer(@PathVariable String id, @Valid @RequestBody UpdateRequest request){

            CustomerDto dto = CustomerDto.builder()
                  .name(request.getName())
                  .documentType(request.getDocumentType())
                  .documentNumber(request.getDocumentNumber())
                  .phone(request.getPhone())
                  .build();

            return ResponseBuilder.ok("Cliente actualizado exitosamente", _customer.updateCustomer(id, dto));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable String id){
            _customer.deleteCustomer(id);
            return ResponseBuilder.deleted("Cliente eliminado exitosamente");
      }

}
