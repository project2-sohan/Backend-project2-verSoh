package com.example.supercoding2stsohee.service;

import com.example.supercoding2stsohee.repository.user.User;
import com.example.supercoding2stsohee.repository.user.UserJpa;
import com.example.supercoding2stsohee.repository.userDetails.CustomUserDetails;
import com.example.supercoding2stsohee.service.exceptions.NotFoundException;
import com.example.supercoding2stsohee.web.dto.ResponseDTO;
import com.example.supercoding2stsohee.web.dto.user.UserBody;
import com.example.supercoding2stsohee.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpa userJpa;
    public ResponseDTO findUserByEmail(CustomUserDetails customUserDetails) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("해당 이메일에 해당하는 유저를 찾을 수 없습니다.", customUserDetails.getEmail()));

        UserResponse userResponse = UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .profileImg(user.getProfileImg())
                .address(user.getAddress())
                .gender(user.getGender())
                .status(user.getStatus())
                .failureCount(user.getFailureCount())
                .build();

        return new ResponseDTO(200, "유저정보 조회 성공", userResponse);
    }


    @Transactional(transactionManager = "tm")
    public ResponseDTO updateUser(CustomUserDetails customUserDetails, UserBody userBody) {
        User user= userJpa.findByEmailFetchJoin(customUserDetails.getEmail())
                .orElseThrow(()-> new NotFoundException("해당 이메일에 해당하는 유저를 찾을 수 없습니다.", customUserDetails.getEmail()));
        user.setPhoneNumber(userBody.getPhoneNumber());
        user.setEmail(userBody.getEmail());
        user.setNickName(userBody.getNickName());
        user.setPassword(userBody.getPassword());
        userJpa.save(user);
        return new ResponseDTO(200, "회원정보 수정 성공");
    }


//    public ResponseDTO getUserCart(CustomUserDetails customUserDetails) {
//
//    }
}
