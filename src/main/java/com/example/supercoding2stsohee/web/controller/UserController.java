package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.UserService;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.UserBody;
import com.example.supercoding2stsohee.web.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
//public class UserController implements ApiController {
public class UserController {
    private final UserService userService;
    //가입정보 조회
    @Operation(summary = "이메일로 유저 찾기")
    @GetMapping("/find/{userEmail}")
    public ResponseDTO findUserByEmail(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        return userService.findUserByEmail(customUserDetails);
    }

    //가입정보 수정
    @Operation(summary = "유저 정보 수정")
    @PutMapping("/update/{userEmail}")
    public ResponseDTO updateUser(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                           @RequestBody UserBody userBody){
        return userService.updateUser(customUserDetails, userBody);
    }

//내 장바구니 조회
//내 구매목록 조회
//주문
}
