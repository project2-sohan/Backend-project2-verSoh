package com.example.supercoding2stsohee.config.security;

import com.example.supercoding2stsohee.service.exceptions.CustomAccessDeniedHandler;
import com.example.supercoding2stsohee.service.exceptions.CustomAuthenticationEntryPoint;
import com.example.supercoding2stsohee.web.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers(h -> h.frameOptions(f -> f.sameOrigin()))
                .csrf(c->c.disable())
                .httpBasic(h->h.disable())
                .formLogin(f->f.disable())
                .rememberMe(r->r.disable())
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(a ->
                                a
                                        .requestMatchers("/resources/static/**", "/sign-up", "/login", "/logout").permitAll() // 로그인 안해도 가능
                                        .requestMatchers("/test").hasRole("USER") // user 권한이 있어야 가능
                        // DB ROLE 테이블에는 ROLE_USER이라고 되어있지만 여기서 USER만 넣어도 앞에 `ROLE_`이 자동으로 붙음
                )

                .exceptionHandling(e->{
                    e.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
                    e.accessDeniedHandler(new CustomAccessDeniedHandler());
                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }


}
