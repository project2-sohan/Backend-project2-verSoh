package com.example.supercoding2stsohee.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTableResponse {
    private Integer orderId;
    private Integer userId;
    private String ship;
    private String orderStatus;
    private String orderRequestContents;
    private Integer order_price;
    private LocalDateTime orderdAt;

}
