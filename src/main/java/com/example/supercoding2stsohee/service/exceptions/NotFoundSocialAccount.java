package com.example.supercoding2stsohee.service.exceptions;

import com.example.supercoding2stsohee.web.dto.sign.SocialAccountDto;
import lombok.Getter;

@Getter
public class NotFoundSocialAccount extends RuntimeException{
    private SocialAccountDto request;
    private String detailMessage;


    public NotFoundSocialAccount(SocialAccountDto request, String detailMessage) {
        this.request = request;
        this.detailMessage = detailMessage;
    }
}
