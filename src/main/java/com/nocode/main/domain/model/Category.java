package com.nocode.main.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Category extends Generic {

    private String name;

    private String description;

    private List<Product> products = new ArrayList<>();

}
