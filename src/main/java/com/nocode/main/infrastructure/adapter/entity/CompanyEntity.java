package com.nocode.main.infrastructure.adapter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompanyEntity extends GenericEntity {
    @Column
    private String name;

    @Column
    private String logo;

    @Column
    private String address;

    @Column
    private String ruc;

    @Column
    private String phone;
}
