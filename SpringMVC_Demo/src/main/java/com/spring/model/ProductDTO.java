package com.spring.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {

    private int id;
    private String name;
    private Double price;
    private String image;
}
