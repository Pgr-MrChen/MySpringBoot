package com.cxd.myspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultDTO <T> {
    private static final int SUCCESS = 200;
    private static final int FAILED = 500;
    private int code;
    private String message;
    private T data;

    public ResultDTO() {
    }

    public ResultDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO( T data) {
        this.data = data;
    }

    public ResultDTO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
