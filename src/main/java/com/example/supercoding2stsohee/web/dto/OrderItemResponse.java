package com.example.supercoding2stsohee.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Integer orderItemId;
    private Integer orderId;
    private Integer productOptionId;
    private Integer itemAmount;
    private Integer orderItemPrice;

}
