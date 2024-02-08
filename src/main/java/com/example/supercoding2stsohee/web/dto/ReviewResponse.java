package com.example.supercoding2stsohee.web.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Integer reviewId;
    private Integer userId;
    private Integer productId;
    private String reviewContents;
    private Integer score;
    private LocalDateTime createdAt;

}
