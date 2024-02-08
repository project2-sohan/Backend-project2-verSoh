package com.example.supercoding2stsohee.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.N;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionResponse {
    private Integer productOptionId;
    private Integer productId;
    private String color;
    private String productSize;
    private Integer stock;

}
