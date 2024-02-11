package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.repository.product.Product;
import com.example.supercoding2stsohee.repository.product.ProductJpa;
import com.example.supercoding2stsohee.repository.productOption.ProductOption;
import com.example.supercoding2stsohee.repository.productOption.ProductOptionJpa;
import com.example.supercoding2stsohee.repository.productPhoto.ProductPhoto;
import com.example.supercoding2stsohee.repository.productPhoto.ProductPhotoJpa;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.exceptions.NotFoundException;
import com.example.supercoding2stsohee.web.dto.product.ProductRequest;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final UserJpa userJpa;
    private final ProductJpa productJpa;
    private final ProductPhotoJpa productPhotoJpa;
    private final ProductOptionJpa productOptionJpa;

    //상품 등록
    public ResponseDTO saveProduct(CustomUserDetails customUserDetails, ProductRequest productRequest) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
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

    public List<ProductResponse> findAllProducts() {
        List<Product> products = productJpa.findAllProducts();
        if(products.isEmpty()) throw new NotFoundException("등록된 판매 상품이 없습니다.");

        List<ProductResponse> productResponse= products
                .stream()
                .map((p)-> )
        return

//        return products.stream().map(ProductResponse::mapFromProduct).collect(Collectors.toList());

    }
}
