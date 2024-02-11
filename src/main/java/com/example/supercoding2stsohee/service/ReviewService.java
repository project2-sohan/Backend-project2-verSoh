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
import com.example.supercoding2stsohee.web.dto.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final UserJpa userJpa;
    private final ProductJpa productJpa;
    private final ReviewJpa reviewJpa;
    public ResponseDTO saveReview(CustomUserDetails customUserDetails, Integer productId, ReviewRequest reviewRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));

        //한솔! 1. product은 어떻게 받아오지?
        //이 product에 대한 댓글이라는 것을 아는 법(여기서 1차때도 막혔음)
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
}
