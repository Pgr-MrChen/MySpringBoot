package com.cxd.myspringboot.shared.enums;

import lombok.Getter;

/**
 * 返回结果枚举
 */
@Getter
public enum ResultEnum {
    SYSTEM_DATA_SAVE_ERROR(2, "数据库保存数据操作失败"),
    USER_SEARCH_ERROR(10, "商店用户查找失败"),
    USER_CREATE_BY_PWD_ERROR(11, "通过密码创建用户失败"),
    USER_CREATE_BY_SMS_ERROR(12, "短信创建用户失败"),
    USER_UPDATE_ERROR(13, "修改用户失败"),
    USER_CREATE_TOKEN_ERROR(21, "商户新建token码失败"),
    USER_UPDATE_TOKEN_ERROR(22, "商户更新token码失败"),
    USER_CREATE_CODE_ERROR(31, "创建用户短信码失败"),
    USER_UPDATE_CODE_ERROR(32, "商户更新短信码失败"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
