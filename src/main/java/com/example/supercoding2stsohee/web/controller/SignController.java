package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.service.AuthService;
import com.example.supercoding2stsohee.web.dto.sign.LoginRequest;
import com.example.supercoding2stsohee.web.dto.sign.SignUpRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class SignController {
    private final AuthService authService;
    @PostMapping("/sign-up")
    public String register(@RequestBody SignUpRequest signUpRequest){
        return authService.signUp(signUpRequest);
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
