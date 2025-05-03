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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserEntity extends GenericEntity {
    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column
    private String address;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String role;

    @Column
    private boolean status;
}
