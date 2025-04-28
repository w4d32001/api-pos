package com.nocode.main.services.shopping.request;

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
public class CreatePurchaseDetailDto {

    private String product;

    private int amount;

    private double purchasePrice;

    private double salePrice;

}
