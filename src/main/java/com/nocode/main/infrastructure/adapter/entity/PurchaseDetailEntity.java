package com.nocode.main.infrastructure.adapter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@Table(name = "purchase_details")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PurchaseDetailEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "shopping_id")
    @JsonIgnore
    private ShoppingEntity shopping;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column
    private int amount;

    @Column(name = "purchase_price")
    private double purchasePrice;

    @Column(name = "sale_price")
    private double salePrice;
}
