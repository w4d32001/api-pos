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
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class CustomerEntity extends GenericEntity {
    @Column
    private String name;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column
    private String phone;
}
