package com.example.supercoding2stsohee.web.dto.cart;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseList {
    private Integer productId;
    private String productName;
    private String productImg;
    private List<CartProductOptionResponse> cartProductOptionResponseList;
    private Integer productTotalPrice;


}
