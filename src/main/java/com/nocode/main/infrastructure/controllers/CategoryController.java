package com.nocode.main.infrastructure.controllers;

import com.nocode.main.application.service.CategoryService;
import com.nocode.main.infrastructure.shared.response.ApiResponse;
import com.nocode.main.infrastructure.shared.response.ResponseBuilder;
import com.nocode.main.domain.model.dto.CategoryDto;
import com.nocode.main.domain.model.dto.request.category.CreateCategoryRequest;
import com.nocode.main.domain.model.dto.request.category.UpdateCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveCategory(@Valid @RequestBody CreateCategoryRequest request) {

        categoryService.saveCategory(request);

        return ResponseBuilder.created("Categoria creada exitosamente");

    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryDto>>> findAllCategories(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CategoryDto> categoryDtoPage = categoryService.findAllCategories(search, page, size);

        return ResponseBuilder.ok("Lista de categorias", categoryDtoPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCategory(@PathVariable String id, @Valid @RequestBody UpdateCategoryRequest request) {
        categoryService.updateCategory(id, request);

        return ResponseBuilder.ok("Categoria actualizada con exito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);

        return ResponseBuilder.deleted("Categoria eliminada exitosamente");
    }

    @GetMapping("name/{name}")
    public ResponseEntity<ApiResponse<CategoryDto>> findByName(@PathVariable String name) {
        CategoryDto categoryDto = categoryService.findByName(name);

        return ResponseBuilder.ok("Categoria encontrada", categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> findById(@PathVariable String id) {
        CategoryDto categoryDto = categoryService.findById(id);

        return ResponseBuilder.ok("Categoria encontrada", categoryDto);
    }

}
