package com.example.supercoding2stsohee.web.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    private Integer addAmount; //장바구니에 추가한 개수
    private Integer totalAmount; //장바구니에 있는 상품 옵션 번호의 총 개수
}
