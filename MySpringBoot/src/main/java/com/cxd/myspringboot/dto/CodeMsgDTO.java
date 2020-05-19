package com.cxd.myspringboot.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Restful返回code和message
 *
 * @Author GerryMC
 * @Date: 2020/5/14 0014 15:12
 */

@Setter
@Getter
public class CodeMsgDTO {
    private int code;
    private String message;

    //系统返回结果
    public static CodeMsgDTO SUCCESS = new CodeMsgDTO(200,"请求成功。");
    public static CodeMsgDTO TOKEN_ERROR = new CodeMsgDTO(400,"token验证失败。");

    //返回结果集 常用结果
    public static CodeMsgDTO RESULT_FAILED = new CodeMsgDTO(0,"操作失败。");
    public static CodeMsgDTO RESULT_SUCCESS = new CodeMsgDTO(1,"操作成功。");
    //返回结果集 4001~4100登陆操作相关结果
    public static CodeMsgDTO USER_NOT_EXIST = new CodeMsgDTO(4001,"用户不存在。");
    public static CodeMsgDTO USER_EXIST = new CodeMsgDTO(4002,"用户已存在。");
    public static CodeMsgDTO PHONE_NOT_EXIST = new CodeMsgDTO(4003,"电话不存在。");
    public static CodeMsgDTO PHONE_EXIST = new CodeMsgDTO(4004,"电话已存在。");
    public static CodeMsgDTO EMAIL_NOT_EXIST = new CodeMsgDTO(4005,"邮箱不存在。");
    public static CodeMsgDTO EMAIL_EXIST = new CodeMsgDTO(4006,"邮箱已存在。");
    public static CodeMsgDTO VER_CODE_ERROR = new CodeMsgDTO(4007,"验证码错误。");
    public static CodeMsgDTO VER_CODE_EXPIRE = new CodeMsgDTO(4008,"验证码已过期。");
    public static CodeMsgDTO SMS_ERROR = new CodeMsgDTO(4009,"发送短信失败。");

    //返回结果集 4101~4200相关结果

    public CodeMsgDTO() {
    }

    public CodeMsgDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
