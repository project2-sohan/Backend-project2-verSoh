package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.config.security.JwtTokenProvider;
import com.example.supercoding2stsohee.repository.roles.Roles;
import com.example.supercoding2stsohee.repository.roles.RolesJpa;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.userRoles.UserRoles;
import com.example.supercoding2stsohee.repository.userRoles.UserRolesJpa;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import com.example.supercoding2stsohee.service.exceptions.AccessDenied;
import com.example.supercoding2stsohee.service.exceptions.BadRequestException;
import com.example.supercoding2stsohee.service.exceptions.NotFoundException;
import com.example.supercoding2stsohee.service.exceptions.NullPointerException;
import com.example.supercoding2stsohee.web.dto.sign.LoginRequest;
import com.example.supercoding2stsohee.web.dto.sign.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager; //securityConfig에 bean추가
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.NotAcceptableStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RolesJpa rolesJpa;
    private final UserRolesJpa userRolesJpa;
    private final UserJpa userJpa;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private static boolean isValidPhoneNumber(String phoneNumber) {
        // 정규 표현식을 사용하여 핸드폰 번호가 01로 시작하고 총 11자리인지 확인합니다.
        String regex = "^01\\d{9}$";
        return Pattern.matches(regex, phoneNumber);
    }


    @Transactional(transactionManager = "tm")
    public String signUp(SignUpRequest signUpRequest) {
        // 이메일 중복 확인
        if(userJpa.existsByEmail(signUpRequest.getEmail()))
            throw new BadRequestException("이미 가입된 이메일입니다.", signUpRequest.getEmail());

        // 연락처 형식 Exception
        if (!isValidPhoneNumber(signUpRequest.getPhoneNumber()))
            throw new BadRequestException("연락처 입력 오류", signUpRequest.getPhoneNumber());

        // 기본 프로필이미지 설정
        String userProfileImg;
        if (signUpRequest.getProfileImg() != null && signUpRequest.getProfileImg().isEmpty()) {
            userProfileImg = signUpRequest.getProfileImg();
        }else{
            userProfileImg = "https://media.istockphoto.com/id/1337144146/vector/default-avatar-profile-icon-vector.jpg?s=612x612&w=0&k=20&c=BIbFwuv7FxTWvh5S3vB6bkT0Qv8Vn8N5Ffseq84ClGI=";
        }

        // 성별은 "남성", 혹은 "여성" 으로 입력필수
        if (!"남성".equals(signUpRequest.getGender()) && !"여성".equals(signUpRequest.getGender()))
            throw new BadRequestException("성별은 '남성' 혹은 '여성' 이여야합니다.", signUpRequest.getGender());

        Roles roles= rolesJpa.findByName("ROLE_USER");

        User user= User.builder()
                .name(signUpRequest.getName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .email(signUpRequest.getEmail())
                .nickName(signUpRequest.getNickName())
                .password(passwordEncoder.encode(signUpRequest.getPassword())) //passwordEncoder
                .profileImg(userProfileImg)
                .address(signUpRequest.getAddress())
                .gender(signUpRequest.getGender())
                .status("normal")
                .failureCount(0)
                .createdAt(LocalDateTime.now())
                .build();
        userJpa.save(user);
        userRolesJpa.save(
                UserRoles.builder()
                        .user(user)
                        .roles(roles)
                        .build()
        );
        return "회원가입 성공";
    }

    public String login(LoginRequest loginRequest) {
        try{
            Authentication authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user= userJpa.findByEmailFetchJoin(loginRequest.getEmail())
                    .orElseThrow(()-> new NotFoundException("해당 이메일에 해당하는 유저를 찾을 수 없습니다.", loginRequest.getEmail()));
            List<String> roles= user.getUserRoles().stream().map(UserRoles::getRoles).map(Roles::getName).collect(Collectors.toList());
            return jwtTokenProvider.createToken(loginRequest.getEmail(), roles);
        }catch(Exception e){
            e.printStackTrace();
            throw new NotAcceptableStatusException("로그인 할 수 없습니다.");
        }
    }
}
