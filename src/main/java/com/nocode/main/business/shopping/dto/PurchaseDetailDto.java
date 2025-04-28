package com.nocode.main.business.shopping.dto;

import com.nocode.main.dtos.ProductDto;
import com.nocode.main.dtos.ShoppingDto;
import com.nocode.main.dtos.SupplierDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PurchaseDetailDto {

    private String product;

    private int amount;

    private double purchasePrice;

    private double salePrice;
}
