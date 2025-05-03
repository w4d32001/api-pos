package com.nocode.main.domain.model.dto.request.shopping;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreatePurchaseDetail {
    @NotBlank(message = "El producto es obligatorio.")
    private String product;

    @Min(value = 1, message = "La cantidad debe ser al menos 1.")
    private int amount;

    @Min(value = 0, message = "El precio de compra debe ser un valor positivo.")
    private double purchasePrice;

    @Min(value = 0, message = "El precio de venta debe ser un valor positivo.")
    private double salePrice;
}
