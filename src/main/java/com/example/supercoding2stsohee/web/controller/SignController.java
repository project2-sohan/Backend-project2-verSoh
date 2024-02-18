package com.example.supercoding2stsohee.web.controller;

import com.example.supercoding2stsohee.config.security.JwtTokenProvider;
import com.example.supercoding2stsohee.service.AuthService;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.sign.LoginRequest;
import com.example.supercoding2stsohee.web.dto.sign.SignUpRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController implements ApiController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
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

    //로그아웃
    @PostMapping("/logout")
    public ResponseDTO logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            String currentToken= jwtTokenProvider.resolveToken(httpServletRequest);
            jwtTokenProvider.addToBlackList(currentToken);
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
        }
        return new ResponseDTO(HttpStatus.OK.value(), "로그아웃 성공");
    }

    //회원탈퇴
    @DeleteMapping("/withdraw")
    public ResponseDTO withdraw(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            String userEmail = jwtTokenProvider.getUserEmail(jwtTokenProvider.resolveToken(httpServletRequest));
            authService.withdrawl(userEmail);

            String currentToken= jwtTokenProvider.resolveToken(httpServletRequest);
            jwtTokenProvider.addToBlackList(currentToken);

            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
        }
        return new ResponseDTO(HttpStatus.OK.value(), "회원탈퇴 성공");

    }
}
