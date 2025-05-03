package com.nocode.main.infrastructure.controllers;

import com.nocode.main.application.service.ShoppingService;
import com.nocode.main.domain.model.dto.request.shopping.CreateShopping;
import com.nocode.main.infrastructure.shared.response.ApiResponse;
import com.nocode.main.infrastructure.shared.response.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shoppings")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(final ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveShopping(@Valid @RequestBody CreateShopping request) {
        shoppingService.saveShopping(request);

        return ResponseBuilder.created("Compra generada exitosamente");
    }

}
