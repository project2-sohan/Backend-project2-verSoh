package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.ReviewService;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.ReviewRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController implements ApiController{
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록")
    @PostMapping("/{productId}/register")
    public ResponseDTO registerReview(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Integer productId,
            @RequestBody ReviewRequest reviewRequest){
        return reviewService.saveReview(customUserDetails, productId, reviewRequest);
    }

//    public ResponseDTO findAllReview(@PathVariable)
}
