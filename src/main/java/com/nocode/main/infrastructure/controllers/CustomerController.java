package com.nocode.main.infrastructure.controllers;

import com.cloudinary.Api;
import com.nocode.main.application.service.CustomerService;
import com.nocode.main.domain.model.dto.CustomerDto;
import com.nocode.main.domain.model.dto.request.customer.CreateCustomer;
import com.nocode.main.domain.model.dto.request.customer.UpdateCustomer;
import com.nocode.main.infrastructure.shared.response.ApiResponse;
import com.nocode.main.infrastructure.shared.response.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CustomerDto>>> findAllCustomers(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<CustomerDto> customerDtos = customerService.findAllCustomers(search, page, size);

        return ResponseBuilder.ok("Lista de clientes", customerDtos);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveCustomer(@Valid @RequestBody CreateCustomer request) {

        customerService.createCustomer(request);

        return ResponseBuilder.created("Cliente creado exitosamente");

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCustomer(@PathVariable String id, @Valid @RequestBody UpdateCustomer request) {
        customerService.updateCustomer(id, request);
        return ResponseBuilder.ok("Cliente actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseBuilder.deleted("Cliente eliminado exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDto>> findById(@PathVariable String id) {
        CustomerDto customerDto = customerService.findById(id);
        return ResponseBuilder.ok("Cliente encontrado", customerDto);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<ApiResponse<CustomerDto>> findByPhone(@PathVariable String phone) {
        CustomerDto customerDto = customerService.findByPhone(phone);
        return ResponseBuilder.ok("Cliente encontrado", customerDto);
    }

    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<ApiResponse<CustomerDto>> findByDocumentNumber(@PathVariable String documentNumber) {
        CustomerDto customerDto = customerService.findByDocumentNumber(documentNumber);
        return ResponseBuilder.ok("Cliente encontrado", customerDto);
    }

}
