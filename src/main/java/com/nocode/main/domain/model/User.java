package com.nocode.main.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends Generic {
    private String name;

    private String email;

    private String password;

    private String phone;

    private String documentType;

    private String documentNumber;

    private String address;

    private String userName;

    private String role;

    private boolean status;
}
