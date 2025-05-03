package com.nocode.main.infrastructure.adapter.entity;

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
@Table(name = "suppliers")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SupplierEntity extends GenericEntity {

    @Column
    private String name;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingEntity> shoppings = new ArrayList<>();
}
