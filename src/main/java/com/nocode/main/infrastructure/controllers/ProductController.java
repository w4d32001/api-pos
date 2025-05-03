package com.nocode.main.infrastructure.controllers;

import com.nocode.main.application.service.ProductService;
import com.nocode.main.domain.model.dto.ProductDto;
import com.nocode.main.domain.model.dto.request.product.CreateProduct;
import com.nocode.main.domain.model.dto.request.product.UpdateProduct;
import com.nocode.main.infrastructure.shared.response.ApiResponse;
import com.nocode.main.infrastructure.shared.response.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> saveProduct(@Valid @ModelAttribute CreateProduct request) {

        productService.saveProduct(request);

        return ResponseBuilder.created("Producto creado exitosamente");

    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductDto>>> findAllProducts(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ProductDto> productDtos = productService.findAllProducts(search, page, size);
        return ResponseBuilder.ok("Lista de productos", productDtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> findById(@PathVariable String id) {
        ProductDto productDto = productService.findById(id);
        return ResponseBuilder.ok("Detalle del producto", productDto);
    }

    @PostMapping(path = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> updateProduct(@PathVariable String id, @Valid @ModelAttribute UpdateProduct request) {
        productService.updateProduct(id, request);
        return ResponseBuilder.ok("Producto actualizado exitosamente");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseBuilder.deleted("Producto eliminado exitosamente");
    }


}
