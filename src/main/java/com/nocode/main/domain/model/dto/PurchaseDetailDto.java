package com.nocode.main.domain.model.dto;

import com.nocode.main.domain.model.Product;
import com.nocode.main.domain.model.Shopping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PurchaseDetailDto extends GenericDto {
    private ShoppingDto shopping;

    private ProductDto product;

    private int amount;

    private double purchasePrice;

    private double salePrice;
}
