package com.example.supercoding2stsohee.web.advice;

import com.example.supercoding2stsohee.service.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.NullPointerException;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidValueException.class)
    public String handleInvalidValueException(InvalidValueException ive){
        log.error(" 클라이언트 요청에 문제가 있습니다. Invalid Value Exception:"+ ive.getMessage());
        return ive.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotAcceptException.class)
    public String handleNotAcceptException(NotAcceptException nae){
        log.error("Not Accept Exception:" + nae.getMessage());
        return nae.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException nfe){
        log.error("Not Found Error message: "+ nfe.getMessage());
        return nfe.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException npe){
        log.error("클라이언트 요청에 문제가 있습니다. Null pointer Exception: "+ npe.getMessage());
        return npe.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughStockException.class)
    public String handleNotEnoughStockException(NotEnoughStockException nese){
        log.error("클라이언트 요청에 문제가 있습니다. Not Enough Stock Exception: "+ nese.getMessage());
        return nese.getMessage();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SoldOutException.class)
    public String handleSoldOutException(SoldOutException soe){
        log.error("클라이언트 요청에 문제가 있습니다. Sold Out Exception: "+ soe.getMessage());
        return soe.getMessage();
    }
}
