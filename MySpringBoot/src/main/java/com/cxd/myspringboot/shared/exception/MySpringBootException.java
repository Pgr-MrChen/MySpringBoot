package com.cxd.myspringboot.shared.exception;

import com.cxd.myspringboot.shared.enums.ResultEnum;

public class MySpringBootException extends RuntimeException {
    private Integer code;

    public MySpringBootException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
