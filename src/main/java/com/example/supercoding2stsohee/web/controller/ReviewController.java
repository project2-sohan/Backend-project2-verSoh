package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.ReviewService;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.review.ReviewRequest;
import com.example.supercoding2stsohee.web.dto.review.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController implements ApiController{
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록")
    @PostMapping("/register/{productId}")
    public ResponseDTO registerReview(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @PathVariable Integer productId,
                                        @RequestBody ReviewRequest reviewRequest){
        return reviewService.saveReview(customUserDetails, productId, reviewRequest);
    }

    @Operation(summary = "리뷰 수정")
    @PutMapping("/update/{reviewId}")
    public ResponseDTO updateReview(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                    @PathVariable Integer reviewId,
                                  @RequestBody ReviewRequest reviewRequest){
        return reviewService.updateReview(customUserDetails, reviewId, reviewRequest);
    }

    @Operation(summary = "사용자가 등록한 리뷰 조회")
    @GetMapping("/find-my-review")
    public ResponseDTO findUserReview(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return reviewService.findUserReview(customUserDetails);
    }

    @Operation(summary = "한 상품에 대한 리뷰 조회")
    @GetMapping("/{productId}/find")
    public ResponseDTO findReviewByProductId(@PathVariable Integer productId){
        return reviewService.findReviewByProductId(productId);
    }


}
