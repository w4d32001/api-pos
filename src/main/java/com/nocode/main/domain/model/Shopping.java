package com.nocode.main.domain.model;

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
public class Shopping extends Generic {
    private Supplier supplier;

    private LocalDateTime date;

    private String voucherType;

    private double tax;

    private double subTotal;

    private double total;

    private List<PurchaseDetail> purchaseDetail = new ArrayList<>();
}
