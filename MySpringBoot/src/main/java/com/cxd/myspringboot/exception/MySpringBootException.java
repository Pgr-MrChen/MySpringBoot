package com.cxd.myspringboot.exception;

import com.cxd.myspringboot.enums.ResultEnum;

public class MySpringBootException extends RuntimeException {
    private Integer code;

    public MySpringBootException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
