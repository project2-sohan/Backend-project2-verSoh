package com.example.supercoding2stsohee.web.dto.cart;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProductOptionResponse {
    private Integer optionId;
    private String color;
    private String size;
    private Integer price;
    private Integer quantity;
}
