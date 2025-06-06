package com.nocode.main.infrastructure.adapter.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "shoppings")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ShoppingEntity extends GenericEntity {
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @Column
    private LocalDateTime date;

    @Column(name = "voucher_type")
    private String voucherType;

    @Column
    private double tax;

    @Column(name = "sub_total")
    private double subTotal;

    @Column
    private double total;

    @OneToMany(mappedBy = "shopping", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PurchaseDetailEntity> purchaseDetail = new ArrayList<>();
}
