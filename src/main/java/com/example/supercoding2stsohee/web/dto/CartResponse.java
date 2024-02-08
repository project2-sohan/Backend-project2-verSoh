package com.example.supercoding2stsohee.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Integer cartId;
    private Integer productOptionId;
    private Integer userId;
    private Integer cartAmount;


}
