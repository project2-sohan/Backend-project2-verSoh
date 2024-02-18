package com.example.supercoding2stsohee.web.filters;

import com.example.supercoding2stsohee.config.security.JwtTokenProvider;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = jwtTokenProvider.resolveToken(request); // Token 에서 원하는 정보를 가져오기
            if (jwtToken != null && !jwtTokenProvider.isTokenBlackListed(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) { // jwtToken 이 존재하고 유효하다면
                Authentication auth = jwtTokenProvider.getAuthentication(jwtToken); // jwtTokenProvider 에서 권한을 가져오고
                SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder.getContext() 에 auth 를 넣어준다.
            }
        } catch(JwtException e) {
            e.printStackTrace();
            throw new JwtException("해당 토큰은 만료되었거나 유효하지 않습니다.");
        }
        filterChain.doFilter(request, response);
    }
}
