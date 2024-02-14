package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.ProductService;
import com.example.supercoding2stsohee.web.dto.product.ProductRequest;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    //상품등록
    @Operation(summary = "새로운 product 등록")
    @PostMapping("/register")
    public ResponseDTO registerProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @RequestBody ProductRequest productRequest){
        return productService.saveProduct(customUserDetails, productRequest);
    }

    @Operation(summary = "모든 상품 일부 정보 조회해 메인 페이지")
    @GetMapping("/find/main-page")
    public ResponseDTO findAllProducts(){
        return productService.findAllProducts();
    }


    @Operation(summary = "하나의 상품에 대해 상세 조회")
    @GetMapping("/find/detail/{productId}")
    public ResponseDTO findProductDetail(@PathVariable Integer productId){
        return productService.findProductDetail(productId);
    }

    @Operation(summary = "카테고리별로 상품에 조회")
    @GetMapping("/find/category")
    public ResponseDTO findProductByCategory(@RequestParam String category){
        return productService.findProductByCategory(category);
    }

    @Operation(summary = "키워드로 상품 조회")
    @GetMapping("/find/{keyword}")
    public ResponseDTO findProductByKeyword(@PathVariable String keyword){
        return productService.findProductByKeyword(keyword);
    }


}
