package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.config.security.JwtTokenProvider;
import com.example.supercoding2stsohee.repository.roles.Roles;
import com.example.supercoding2stsohee.repository.roles.RolesJpa;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.userRoles.UserRoles;
import com.example.supercoding2stsohee.repository.userRoles.UserRolesJpa;
import com.example.supercoding2stsohee.repository.user.UserJpa;
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

    @Transactional(transactionManager = "tm")
    public boolean signUp(SignUpRequest signUpRequest) {
        if(userJpa.existsByEmail(signUpRequest.getEmail())){
            return false;
        }

        Roles roles= rolesJpa.findByName("ROLE_USER");

        User user= User.builder()
                .name(signUpRequest.getName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .email(signUpRequest.getEmail())
                .nickName(signUpRequest.getNickName())
                .password(passwordEncoder.encode(signUpRequest.getPassword())) //passwordEncoder
                .profileImg(signUpRequest.getProfileImg())
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
        return true;
    }

    public String login(LoginRequest loginRequest) {
        try{
            Authentication authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user= userJpa.findByEmailFetchJoin(loginRequest.getEmail())
                    .orElseThrow(()-> new NullPointerException("해당 이메일로 계정을 찾을 수 없습니다."));
            List<String> roles= user.getUserRoles().stream().map(UserRoles::getRoles).map(Roles::getName).collect(Collectors.toList());
            return jwtTokenProvider.createToken(loginRequest.getEmail(), roles);
        }catch(Exception e){
            e.printStackTrace();
            throw new NotAcceptableStatusException("로그인 할 수 없습니다.");
        }
    }
}
