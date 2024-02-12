package com.example.supercoding2stsohee.web.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseList {
    private Integer productId;
    private Integer productName;
    private Integer productImg;
    private List<CartProductOptionResponse> cartProductOptionResponseList;
    private Integer productTotalPrice;


}
