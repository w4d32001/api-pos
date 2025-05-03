package com.nocode.main.domain.model.dto;

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
public class CompanyDto extends GenericDto {
    private String logo;

    private String name;

    private String address;

    private String ruc;

    private String phone;
}
