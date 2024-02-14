package com.example.supercoding2stsohee.service.exceptions;

import lombok.Getter;

@Getter
public class AccountLockedException extends RuntimeException{
    private String detailMessage;
    private String request;

    public AccountLockedException(String detailMessage, String request) {
        this.detailMessage = detailMessage;
        this.request = request;
    }
}
