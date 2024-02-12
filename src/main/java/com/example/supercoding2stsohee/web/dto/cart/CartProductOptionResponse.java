package com.example.supercoding2stsohee.web.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductOptionResponse {
    private Integer optionId;
    private String color;
    private String size;
    private Integer price;
    private Integer quantity;
}
