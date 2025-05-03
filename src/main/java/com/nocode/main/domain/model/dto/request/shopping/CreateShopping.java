package com.nocode.main.domain.model.dto.request.shopping;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateShopping {
    @NotBlank(message = "El proveedor es obligatorio.")
    private String supplier;

    @NotBlank(message = "El tipo de voucher es obligatorio.")
    private String voucherType;

    @Min(value = 0, message = "El impuesto debe ser un valor positivo.")
    private double tax;

    @NotNull(message = "Los detalles de la compra son obligatorios.")
    private List<CreatePurchaseDetail> details;
}
