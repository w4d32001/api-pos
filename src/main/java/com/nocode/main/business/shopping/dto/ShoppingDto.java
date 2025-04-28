package com.nocode.main.business.shopping.dto;

import com.nocode.main.dtos.SupplierDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ShoppingDto {

    private String supplier;

    private String voucherType;

    private double tax;

    private List<PurchaseDetailDto> details;
}
