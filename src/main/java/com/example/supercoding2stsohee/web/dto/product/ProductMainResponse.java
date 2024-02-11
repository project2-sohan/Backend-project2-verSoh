package com.example.supercoding2stsohee.web.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMainResponse {
    private String productName;
    private Integer productPrice;
    private List<PhotoRequestDto> photoRequestDtoList;
    private List<ProductOptionRequestDto> productOptionRequestList;
    private Integer score;

}
