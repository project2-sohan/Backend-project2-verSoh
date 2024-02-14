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
                .orElseThrow(()-> new NotFoundException("해당 이메일에 해당하는 유저를 찾을 수 없습니다.", customUserDetails.getEmail()));

        Product product= productJpa.findById(productId)
                .orElseThrow(()->new NotFoundException("해당 아이디로 상품을 찾을 수 없습니다.", productId));

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
                .orElseThrow(()-> new NotFoundException("해당 이메일에 해당하는 유저를 찾을 수 없습니다.", customUserDetails.getEmail()));
        Review review= reviewJpa.findById(reviewId)
                .orElseThrow(()-> new NotFoundException("해당 리뷰가 존재하지 않습니다.", reviewId));
        review.setReviewContents(reviewRequest.getReviewContents());
        review.setScore(reviewRequest.getScore());

        reviewJpa.save(review);
        return new ResponseDTO(200, "리뷰 수정 완료");
    }

    public ResponseDTO findUserReview(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("해당 이메일에 해당하는 유저를 찾을 수 없습니다.", customUserDetails.getEmail()));
//        Review review= reviewJpa.findById(customUserDetails.getUserId())
//                .orElseThrow(()-> new NotFoundException("유저"+ customUserDetails.getUsername() + "가 작성한 리뷰가 없습니다."));

        List<Review> reviews= reviewJpa.findByUserId(customUserDetails.getUserId()); //JPQL?

        List<ReviewResponse> reviewResponses= reviews.stream().map((r)->ReviewResponse.builder()
                .reviewContents(r.getReviewContents())
                .score(r.getScore())
                .createdAt(r.getCreatedAt())
                .build())
                .collect(Collectors.toList());
        return new ResponseDTO(200, "유저가 작성한 후기 조회 성공", reviewResponses);

    }

    public ResponseDTO findReviewByProductId(Integer productId) {
        Product product = productJpa.findById(productId)
                .orElseThrow(() -> new NotFoundException("해당 상품이 존재하지 않습니다.", productId));
        List<Review> reviews= reviewJpa.findByProduct(product);

        List<ReviewResponse> reviewResponses= reviews.stream().map((r)->ReviewResponse.builder()
                        .reviewContents(r.getReviewContents())
                        .score(r.getScore())
                        .createdAt(r.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
        return new ResponseDTO(200, "상품 아이디별로 후기 조회 성공", reviewResponses);


    }
}
