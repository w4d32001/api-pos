package com.nocode.main.dtos;

import com.nocode.main.entities.PurchaseDetailEntity;
import com.nocode.main.entities.SupplierEntity;
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
public class ShoppingDto extends GenericDto {

    private String id;

    private SupplierDto supplier;

    private LocalDateTime date;

    private String voucherType;

    private double tax;

    private double subTotal;

    private double total;

    private List<PurchaseDetailDto> purchaseDetail = new ArrayList<>();

}
