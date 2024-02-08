package com.example.supercoding2stsohee.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer userId;
    private String name;
    private String phoneNumber;
    private String email;
    private String nickName;
    private String password;
    private String profile_img;
    private String address;
    private String gender;
    private String status;
    private Integer failureCount;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime lockedAt;


}
