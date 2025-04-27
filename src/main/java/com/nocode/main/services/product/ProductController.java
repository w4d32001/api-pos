package com.nocode.main.services.product;

import com.nocode.main.business.product.ProductBusiness;
import com.nocode.main.business.product.dto.ResponseProductDto;
import com.nocode.main.commos.ApiResponse;
import com.nocode.main.commos.ResponseBuilder;
import com.nocode.main.dtos.ProductDto;
import com.nocode.main.services.product.request.StoreRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductBusiness _product;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ResponseProductDto>>> findAllProducts(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        Page<ResponseProductDto> productDtos = _product.findAllProducts(search, page, size);
        return  ResponseBuilder.ok("Lista de productos", productDtos);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> createProduct(@Valid @ModelAttribute    StoreRequest request){
        ProductDto dto = ProductDto.builder()
                .imageUrl(request.getImageUrl())
                .stock(request.getStock())
                .categories(request.getCategories())
                .name(request.getName())
                .description(request.getDescription())
                .build();

        _product.createProduct(dto);

        return ResponseBuilder.created("Producto creado exitosamente");
    }

    @PostMapping(path = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Void>> updateProduct(@PathVariable String id, StoreRequest request){
        ProductDto dto = ProductDto.builder()
                .imageUrl(request.getImageUrl())
                .stock(request.getStock())
                .categories(request.getCategories())
                .name(request.getName())
                .description(request.getDescription())
                .build();

        _product.updateProduct(id, dto);

        return  ResponseBuilder.ok("Producto actualizado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String id){

        _product.deleteProduct(id);

        return ResponseBuilder.deleted("Producto eliminado exitosamente");

    }

}
