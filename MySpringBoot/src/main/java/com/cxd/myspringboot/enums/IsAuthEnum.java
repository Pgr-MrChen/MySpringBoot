package com.cxd.myspringboot.enums;

import lombok.Getter;

/**
 * 身份认证
 */
@Getter
public enum IsAuthEnum {
    NO_AUTH(0, "未认证"),
    IS_AUTH(1, "已认证"),
    ;

    private Integer code;
    private String msg;

    IsAuthEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
