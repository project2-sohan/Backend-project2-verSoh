package com.example.supercoding2stsohee.web.dto.product;

import com.example.supercoding2stsohee.web.dto.review.ReviewResponse;
import com.example.supercoding2stsohee.web.dto.review.ReviewUserDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponse {
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private String category;
    private String productStatus;
    private LocalDateTime createAt;
    private LocalDateTime finishAt;
    private Double scoreAvg;
    private List<PhotoRequestDto> photoRequestDtoList;
    private List<ProductOptionRequestDto> productOptionRequestList;
    private List<ReviewUserDto> reviewUserDtoList;
}
