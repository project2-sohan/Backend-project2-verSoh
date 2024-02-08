package com.example.supercoding2stsohee.web.advice;

import com.example.supercoding2stsohee.service.exceptions.InvalidValueException;
import com.example.supercoding2stsohee.service.exceptions.NotAcceptException;
import com.example.supercoding2stsohee.service.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
