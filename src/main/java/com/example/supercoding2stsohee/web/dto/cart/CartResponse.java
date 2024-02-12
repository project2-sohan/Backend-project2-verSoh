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
public class CartResponse {
    private List<CartResponseList> cartResponseListList;
    private Integer totalQuantity;
    private Integer cartTotalPrice;

}
