package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.repository.cart.Cart;
import com.example.supercoding2stsohee.repository.cart.CartJpa;
import com.example.supercoding2stsohee.repository.product.Product;
import com.example.supercoding2stsohee.repository.product.ProductJpa;
import com.example.supercoding2stsohee.repository.productPhoto.ProductPhoto;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.exceptions.NotFoundException;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.cart.CartProductOptionResponse;
import com.example.supercoding2stsohee.web.dto.cart.CartResponse;
import com.example.supercoding2stsohee.web.dto.cart.CartResponseList;
import com.example.supercoding2stsohee.web.dto.user.UserBody;
import com.example.supercoding2stsohee.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpa userJpa;
    private final CartJpa cartJpa;
    private final ProductJpa productJpa;
    public ResponseDTO findUserByEmail(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));

        UserResponse userResponse = UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .profileImg(user.getProfileImg())
                .address(user.getAddress())
                .gender(user.getGender())
                .status(user.getStatus())
                .failureCount(user.getFailureCount())
                .build();

        return new ResponseDTO(200, "유저정보 조회 성공", userResponse);
    }


    @Transactional(transactionManager = "tm")
    public ResponseDTO updateUser(CustomUserDetails customUserDetails, UserBody userBody) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));
        user.setPhoneNumber(userBody.getPhoneNumber());
        user.setEmail(userBody.getEmail());
        user.setNickName(userBody.getNickName());
        user.setPassword(userBody.getPassword());
        userJpa.save(user);
        return new ResponseDTO(200, "회원정보 수정 성공");
    }


    public ResponseDTO getUserCart(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("이메일" + customUserDetails.getEmail() + "을 가진 유저를 찾지 못했습니다."));

        List<Cart> carts= cartJpa.findByUser(user);

        if(carts.isEmpty()){
            return new ResponseDTO(200, "장바구니가 비었습니다");
        }


        List<CartResponseList> cartResponseLists= carts.stream()
                .filter(cart-> !cart.getProductOption().getProduct().getProductStatus().equals("soldOut"))
                .map((cart)-> {
                    Product product= cart.getProductOption().getProduct();

                    List<String> productImgUrl= product.getProductPhotos().stream()
                            .filter(ProductPhoto::getPhotoType)
                            .map(ProductPhoto::getPhotoUrl)
                            .collect(Collectors.toList());

                    CartProductOptionResponse cartProductOptionResponse = CartProductOptionResponse
                            .builder()
                            .optionId(cart.getProductOption().getProductOptionId())
                            .color(cart.getProductOption().getColor())
                            .size(cart.getProductOption().getProductSize())
                            .price(product.getProductPrice())
                            .quantity(cart.getCartAmount())
                            .build();

                    return CartResponseList.builder()
                            .productId(product.getProductId())
                            .productName(product.getProductName())
                            .productImg(productImgUrl.get(0))
                            .cartProductOptionResponseList(List.of(cartProductOptionResponse))
                            .productTotalPrice(cartProductOptionResponse.getPrice() * cartProductOptionResponse.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());

        Integer totalQuantity = carts.stream().mapToInt(Cart::getCartAmount).sum();
        Integer cartTotalPrice= cartResponseLists.stream().mapToInt(CartResponseList::getProductTotalPrice).sum();

        CartResponse cartResponse= CartResponse.builder()
                .cartResponseLists(cartResponseLists)
                .totalQuantity(totalQuantity)
                .cartTotalPrice(cartTotalPrice)
                .build();

        return new ResponseDTO(200, "사용자의 장바구니 조회 성공", cartResponse);
    }
}
