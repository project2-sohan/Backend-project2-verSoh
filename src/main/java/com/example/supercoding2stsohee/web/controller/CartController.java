package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.CartService;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "장바구니에 상품 등록")
    @PostMapping("/add/{productOptionId}/{addAmount}") //DTO만들기 귀찮고 숫자 두개라 pathvariable로 던짐
    public ResponseDTO addToCart(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                 @PathVariable Integer productOptionId,
                                 @PathVariable Integer addAmount ){
        return cartService.addToCart(customUserDetails, productOptionId, addAmount);
    }

}
