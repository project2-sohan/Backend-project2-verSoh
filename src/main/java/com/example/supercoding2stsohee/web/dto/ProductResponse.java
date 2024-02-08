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
public class ProductResponse {
    private Integer productId;
    private Integer userId;
    private String productName;
    private Integer productPrice;
    private String category;
    private String productStatus;
    private Float rating;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

}
