package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.repository.product.Product;
import com.example.supercoding2stsohee.repository.product.ProductJpa;
import com.example.supercoding2stsohee.repository.review.Review;
import com.example.supercoding2stsohee.repository.review.ReviewJpa;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.exceptions.NotFoundException;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.review.ReviewRequest;
import com.example.supercoding2stsohee.web.dto.review.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final UserJpa userJpa;
    private final ProductJpa productJpa;
    private final ReviewJpa reviewJpa;
    public ResponseDTO saveReview(CustomUserDetails customUserDetails, Integer productId, ReviewRequest reviewRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));

        Product product= productJpa.findById(productId)
                .orElseThrow(()->new NotFoundException("아이디" + productId + "를 가진 상품이 없습니다."));

        Review review= Review.builder()
                .user(user)
                .product(product)
                .reviewContents(reviewRequest.getReviewContents())
                .score(reviewRequest.getScore())
                .createdAt(LocalDateTime.now())
                .build();

        reviewJpa.save(review);
        return new ResponseDTO(200, "댓글 작성 성공");
    }

    @Transactional(transactionManager = "tm")
    public ResponseDTO updateReview(CustomUserDetails customUserDetails, Integer reviewId, ReviewRequest reviewRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        Review review= reviewJpa.findById(reviewId)
                .orElseThrow(()-> new NotFoundException("아이디"+reviewId +"를 가진 리뷰를 찾지 못했습니다. "));
        review.setReviewContents(reviewRequest.getReviewContents());
        review.setScore(reviewRequest.getScore());

        reviewJpa.save(review);
        return new ResponseDTO(200, "리뷰 수정 완료");
    }

    public List<ReviewResponse> findReview(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
//        Review review= reviewJpa.findById(customUserDetails.getUserId())
//                .orElseThrow(()-> new NotFoundException("유저"+ customUserDetails.getUsername() + "가 작성한 리뷰가 없습니다."));

        List<Review> reviews= reviewJpa.findByUserId(customUserDetails.getUserId()); //JPQL?

        List<ReviewResponse> reviewResponses= reviews.stream().map((r)->ReviewResponse.builder()
                .reviewContents(r.getReviewContents())
                .score(r.getScore())
                .createdAt(r.getCreatedAt())
                .build())
                .collect(Collectors.toList());
        return reviewResponses;

    }
}
