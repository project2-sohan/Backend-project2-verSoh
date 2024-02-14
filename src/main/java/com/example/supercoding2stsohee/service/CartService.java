package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.repository.cart.Cart;
import com.example.supercoding2stsohee.repository.cart.CartJpa;
import com.example.supercoding2stsohee.repository.productOption.ProductOption;
import com.example.supercoding2stsohee.repository.productOption.ProductOptionJpa;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.exceptions.NotFoundException;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.cart.CartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserJpa userJpa;
    private final CartJpa cartJpa;
    private final ProductOptionJpa productOptionJpa;

    public ResponseDTO addToCart(CustomUserDetails customUserDetails, Integer productOptionId, Integer addAmount) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("해당 이메일에 해당하는 유저를 찾을 수 없습니다.", customUserDetails.getEmail()));
        ProductOption productOption= productOptionJpa.findById(productOptionId)
                .orElseThrow(()-> new NotFoundException("해당 아이디를 가진 옵션을 찾지 못했습니다.", productOptionId));
        Cart cart= Cart.builder()
                .productOption(productOption)
                .user(user)
                .cartAmount(addAmount)
                .build();
        cartJpa.save(cart);
        return new ResponseDTO(200, "장바구니에 상품 추가 성공");
    }

}
