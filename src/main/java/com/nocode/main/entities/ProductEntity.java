package com.nocode.main.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "t_product")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductEntity extends GenericEntity{

    @Id
    private String id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    @Column
    private int stock;

    @Column
    private String description;

    @Column
    private String image;

    @Column
    private String barCode;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseDetailEntity> purchaseDetail = new ArrayList<>();

}
