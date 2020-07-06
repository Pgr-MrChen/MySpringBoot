package com.cxd.myspringboot.dto.resultful;

import lombok.Getter;
import lombok.Setter;

/**
 * Restful类
 * @param <T>
 */

@Setter
@Getter
public class ResultDTO <T> {
    private int code;
    private String message;
    private T data;

    private ResultDTO() {
    }

    private ResultDTO(CodeMsgDTO codeMsgDTO) {
        this.code = codeMsgDTO.getCode();
        this.message = codeMsgDTO.getMessage();
    }

    private ResultDTO( T data) {
        this.code = 200;
        this.message = "请求成功。";
        this.data = data;
    }

    private ResultDTO(CodeMsgDTO codeMsgDTO, T data) {
        this.code = codeMsgDTO.getCode();
        this.message = codeMsgDTO.getMessage();
        this.data = data;
    }


    //请求成功，不需返回参数
    public static ResultDTO success() {
        return success(null);
    }

    //请求成功，需返回参数
    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<T>(data);
    }

    //请求失败时调用
    public static ResultDTO error(CodeMsgDTO codeMsgDTO) {
        return new ResultDTO(codeMsgDTO);
    }
}
