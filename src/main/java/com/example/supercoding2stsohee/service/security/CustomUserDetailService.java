package com.example.supercoding2stsohee.service.security;

import com.example.supercoding2stsohee.repository.roles.Roles;
import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.repository.userRoles.UserRoles;
import com.example.supercoding2stsohee.service.exceptions.NullPointerException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary //이 bean이 default
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpa userJpa;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userJpa.findByEmailFetchJoin(email)
                .orElseThrow(()-> new NullPointerException("email에 해당하는 user를 찾을 수 없습니다."));

        // 해당 user의 email 로 조회한 정보를 CustomUserDetails repository에 빌더로 넣어준다.

        return CustomUserDetails.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getUserRoles().stream().map(UserRoles::getRoles).map(Roles::getName).collect(Collectors.toList()))
                .build();
    }
}
