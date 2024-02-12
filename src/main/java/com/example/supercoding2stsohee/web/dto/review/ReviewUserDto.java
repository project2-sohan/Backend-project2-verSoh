package com.example.supercoding2stsohee.web.dto.review;

import io.swagger.models.auth.In;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReviewUserDto {
        private Integer userId;
        private Integer reviewId;
        private String reviewContents;
        private Integer score;
        private LocalDateTime createdAt;
}
