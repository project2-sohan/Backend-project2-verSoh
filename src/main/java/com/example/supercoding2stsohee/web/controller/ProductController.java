package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.repository.product.Product;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.ProductService;
import com.example.supercoding2stsohee.web.dto.product.ProductRequest;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.product.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController{

    private final ProductService productService;

    //상품등록

    @Operation(summary = "새로운 product 생성")
    @PostMapping("/register")
    public ResponseDTO registerProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @RequestBody ProductRequest productBody){
        return productService.saveProduct(customUserDetails, productBody);
    }

    @Operation(summary = "모든 상품 조회")
    @GetMapping("/find")
    public List<ProductResponse> findAllProducts(){
        return productService.findAllProducts();
    }

}
