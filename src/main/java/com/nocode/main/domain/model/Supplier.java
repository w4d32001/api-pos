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
public class Supplier extends Generic {
    private String name;

    private String documentType;

    private String documentNumber;

    private String address;

    private String phone;

    private String email;
}
