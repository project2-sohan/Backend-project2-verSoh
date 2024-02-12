package com.example.supercoding2stsohee.web.dto.product;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductMainResponse {
    private Integer productId;
    private Integer userId;
    private String productName;
    private Integer productPrice;
    private String category;
    private LocalDateTime createAt;
    private String mainPhotoUrl;
    private Integer reviewCount;
    private Float scoreAvg;

    public ProductMainResponse(Integer productId, Integer userId, String productName, Integer productPrice, String category, LocalDateTime createAt, String mainPhotoUrl, Long reviewCount, Double scoreAvg) {
        this.productId = productId;
        this.userId = userId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.category = category;
        this.createAt = createAt;
        this.mainPhotoUrl = mainPhotoUrl;
        this.reviewCount = reviewCount != null ? reviewCount.intValue() : 0;
        this.scoreAvg = scoreAvg != null ? scoreAvg.floatValue() : 0;
    }
}
