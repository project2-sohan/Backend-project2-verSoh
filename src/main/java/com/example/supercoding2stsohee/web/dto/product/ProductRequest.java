package com.example.supercoding2stsohee.web.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String productName;
    private Integer productPrice;
    private String category;
    private String productStatus;
    private List<PhotoRequestDto> photoRequestDtoList;
    private List<ProductOptionRequestDto> productOptionRequestList;
}
