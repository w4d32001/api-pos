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
@Table(name = "t_supplier")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SupplierEntity extends GenericEntity {

    @Id
    private String id;

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

}
