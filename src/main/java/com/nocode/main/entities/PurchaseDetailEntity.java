package com.nocode.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@Table(name = "t_purchase_detail")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PurchaseDetailEntity extends GenericEntity{

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "shopping_id")
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
