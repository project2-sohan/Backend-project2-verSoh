package com.example.supercoding2stsohee.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public ResponseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDTO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
