package com.example.supercoding2stsohee.web.dto.cart;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private List<CartResponseList> cartResponseLists;
    private Integer totalQuantity;
    private Integer cartTotalPrice;

}
