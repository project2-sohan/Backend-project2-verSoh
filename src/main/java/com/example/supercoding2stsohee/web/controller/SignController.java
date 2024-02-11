package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.service.AuthService;
import com.example.supercoding2stsohee.web.dto.LoginRequest;
import com.example.supercoding2stsohee.web.dto.SignUpRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController{
    private final AuthService authService;
    @PostMapping("/sign-up")
    public String register(@RequestBody SignUpRequest signUpRequest){
        boolean isSuccess= authService.signUp(signUpRequest);
        return isSuccess ? "회원가입 성공" : "회원가입 실패";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        String token= authService.login(loginRequest);
        httpServletResponse.setHeader("Token", token);
        return "로그인 성공";
    }

    //이메일 중복 체크
    //회원탈퇴
}
