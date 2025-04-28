package com.nocode.main.services.shopping.request;

import com.nocode.main.business.shopping.dto.PurchaseDetailDto;
import com.nocode.main.dtos.ProductDto;
import com.nocode.main.dtos.SupplierDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateShoppingDto {

    private String supplier;

    private String voucherType;

    private double tax;

    private List<CreatePurchaseDetailDto> details;
}
