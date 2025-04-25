package com.nocode.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@Table(name = "t_category")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CategoryEntity extends GenericEntity {
    @Id
    private String id;

    @Column(unique = true)
    private String name;

    @Column
    private String description;

}
