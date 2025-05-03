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
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CategoryEntity extends GenericEntity {

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<ProductEntity> products = new ArrayList<>();

}