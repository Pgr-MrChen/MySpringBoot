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

    //返回结果集
    public static CodeMsgDTO RESULT_FAILED = new CodeMsgDTO(2,"失败。");
    public static CodeMsgDTO USER_NOT_EXIST = new CodeMsgDTO(3,"用户不存在。");
    public static CodeMsgDTO USER_EXIST = new CodeMsgDTO(4,"用户已存在。");


    public CodeMsgDTO() {
    }

    public CodeMsgDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
