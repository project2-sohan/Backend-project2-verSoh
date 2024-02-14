package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.repository.product.Product;
import com.example.supercoding2stsohee.repository.product.ProductJpa;
import com.example.supercoding2stsohee.repository.productOption.ProductOption;
import com.example.supercoding2stsohee.repository.productOption.ProductOptionJpa;
import com.example.supercoding2stsohee.repository.productPhoto.ProductPhoto;
import com.example.supercoding2stsohee.repository.productPhoto.ProductPhotoJpa;
import com.example.supercoding2stsohee.repository.review.Review;
import com.example.supercoding2stsohee.repository.review.ReviewJpa;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.exceptions.NotFoundException;
import com.example.supercoding2stsohee.web.dto.product.*;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.review.ReviewResponse;
import com.example.supercoding2stsohee.web.dto.review.ReviewUserDto;
import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final UserJpa userJpa;
    private final ProductJpa productJpa;
    private final ProductPhotoJpa productPhotoJpa;
    private final ProductOptionJpa productOptionJpa;
    private final ReviewJpa reviewJpa;


    //상품 등록
    public ResponseDTO saveProduct(CustomUserDetails customUserDetails, ProductRequest productRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("해당 이메일에 해당하는 유저를 찾을 수 없습니다.", customUserDetails.getEmail()));
        Product product= Product.builder()
                .user(user)
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .category(productRequest.getCategory())
                .productStatus(productRequest.getProductStatus())
                .rating(0F)
                .createdAt(LocalDateTime.now())
                .build();
        productJpa.save(product);
        
        List<ProductPhoto> productPhotos = productRequest.getPhotoRequestDtoList()
                .stream()
                .map((pp)-> ProductPhoto.builder()
                        .product(product)
                        .photoUrl(pp.getPhotoUrl())
                        .photoType(pp.getPhotoType())
                        .build())
                .toList();
        productPhotoJpa.saveAll(productPhotos);

//        for (int i = 0; i < productPhotos.size(); i++) {
//            productPhotoJpa.save(productPhotos.get(i));
//        }

        List<ProductOption> productOptions = productRequest.getProductOptionRequestList()
                .stream()
                .map((po)->ProductOption.builder()
                        .product(product)
                        .productSize(po.getProductSize())
                        .color(po.getColor())
                        .stock(po.getStock())
                        .build())
                .collect(Collectors.toList());
        productOptionJpa.saveAll(productOptions);

//        ProductResponse productResponse= ProductResponse.builder()
//                .productId(product.getProductId())
//                .userName(product.getUser().getName())
//                .productName(product.getProductName())
//                .productPrice(product.getProductPrice())
//                .category(product.getCategory())
//                .productStatus(product.getProductStatus())
//                .rating(product.getRating())
//                .build();
//
//        return new ResponseDTO(200, "상품 등록 성공", productResponse);
                return new ResponseDTO(200, "상품 등록 성공");
    }



    public ResponseDTO findAllProducts() {
        List<ProductMainResponse> products = productJpa.findAllProducts();
        if(products.isEmpty()) throw new NotFoundException("등록된 판매 상품이 없습니다.", "");

        return new ResponseDTO(200, "메인 페이지 상품 보이기 성공", products);
    }

    public ResponseDTO findProductDetail(Integer productId) {
        Product product = productJpa.findById(productId)
                .orElseThrow(() -> new NotFoundException("해당 상품을 찾을 수 없습니다.", productId));

        List<Review> reviews = reviewJpa.findByProduct(product);
        Double reviewAvg = reviews.stream().collect(Collectors.averagingDouble(Review::getScore));

        List<ProductPhoto> productPhotos = productPhotoJpa.findByProduct(product);
        List<PhotoRequestDto> photoRequestDtoList = productPhotos
                .stream()
                .map((p) -> new PhotoRequestDto(p.getPhotoUrl(), p.getPhotoType()))
                .collect(Collectors.toList());

        List<ProductOption> productOption = productOptionJpa.findByProduct(product);
        List<ProductOptionRequestDto> productOptionRequestDtoList = productOption
                .stream()
                .map((p) -> new ProductOptionRequestDto(p.getProductOptionId(), p.getColor(), p.getProductSize(), p.getStock()))
                .collect(Collectors.toList());

        List<ReviewUserDto> reviewUserDtoList = reviews
                .stream()
                .map((r) -> new ReviewUserDto(r.getUser().getUserId(), r.getReviewId(), r.getReviewContents(), r.getScore(), r.getCreatedAt()))
                .collect(Collectors.toList());


        ProductDetailResponse productDetailResponse = ProductDetailResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .createAt(product.getCreatedAt())
                .finishAt(product.getFinishedAt())
                .scoreAvg(reviewAvg)
                .photoRequestDtoList(photoRequestDtoList)
                .productOptionRequestList(productOptionRequestDtoList)
                .reviewUserDtoList(reviewUserDtoList)
                .build();

        return new ResponseDTO(200, "제품 상세 조회 성공", productDetailResponse);

    }

    public ResponseDTO findProductByCategory(String category) {

        String[] categories = {"top", "pants", "dress", "shoe" };

        List<ProductMainResponse> products = productJpa.findAllProducts();

        List<ProductMainResponse> productCategory = products.stream().filter((p)-> p.getCategory().equals(category)).toList();

        return new ResponseDTO(200, "카테고리별로 제품 조회 성공", productCategory);
    }

    public ResponseDTO findProductByKeyword(String keyword) {
        String checkKeyword= keyword.toLowerCase();
        List<ProductMainResponse> products = productJpa.findAllProducts();

        List<ProductMainResponse> productKeyword= products.stream().filter((p)->p.getProductName().toLowerCase().contains(checkKeyword)).toList();

        return new ResponseDTO(200, "키워드로 제품 조회 성공", productKeyword);
    }
}
