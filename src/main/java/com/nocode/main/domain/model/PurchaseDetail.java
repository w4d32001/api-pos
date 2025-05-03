package com.nocode.main.domain.model;

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
public class PurchaseDetail extends Generic {
    private Shopping shopping;

    private Product product;

    private int amount;

    private double purchasePrice;

    private double salePrice;
}
