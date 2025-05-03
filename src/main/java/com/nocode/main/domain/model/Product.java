package com.nocode.main.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Product extends Generic {

    private String name;

    private List<Category> categories = new ArrayList<>();

    private int stock;

    private String description;

    private String image;

    private String barCode;

}
