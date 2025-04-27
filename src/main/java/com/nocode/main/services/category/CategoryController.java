package com.nocode.main.services.category;

import com.nocode.main.business.CategoryBusiness;
import com.nocode.main.commos.ApiResponse;
import com.nocode.main.commos.ResponseBuilder;
import com.nocode.main.dtos.CategoryDto;
import com.nocode.main.services.category.request.StoreRequest;
import com.nocode.main.services.category.request.UpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryBusiness _category;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryDto>>> findAllCategories(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<CategoryDto> categories = _category.findAllCategories(search, page, size);

        return ResponseBuilder.ok("Lista de categorias", categories);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCategory(@Valid @RequestBody StoreRequest request){

        CategoryDto dto = CategoryDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        _category.createCategory(dto);

        return ResponseBuilder.created("Categoría creada exitosamente");

    }

    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse<Void>> updateCategory(@PathVariable String id, @Valid @RequestBody UpdateRequest request){

      System.out.println("asdasdasdasdasdasdasdasdasdasd"+request);
        CategoryDto dto = CategoryDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        _category.updateCategory(id, dto);

        return  ResponseBuilder.ok("Categoría actualizada exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable String id){
        _category.deleteCategory(id);
        return ResponseBuilder.deleted("Categoría eliminada exitosamente");
    }
}
