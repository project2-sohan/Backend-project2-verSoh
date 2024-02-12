package com.example.supercoding2stsohee.web.dto.review;

import com.example.supercoding2stsohee.repository.product.Product;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private String reviewContents;
    private Integer score;
    private LocalDateTime createdAt;
}
