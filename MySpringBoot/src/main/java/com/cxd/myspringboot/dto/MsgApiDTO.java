package com.cxd.myspringboot.dto;

import lombok.Data;

@Data
public class MsgApiDTO {
    //授权账号
    private String authName;

    //授权Token
    private String authToken;

    //发送电话
    private String smsPhone;

    //发送头部
    private String smsTitle;

    //发送内容
    private String smsContent;

    public MsgApiDTO() {
    }

    public MsgApiDTO(String authName, String authToken, String smsPhone, String smsTitle, String smsContent) {
        this.authName = authName;
        this.authToken = authToken;
        this.smsPhone = smsPhone;
        this.smsTitle = smsTitle;
        this.smsContent = smsContent;
    }

    public String getParams() {
        return "authName=" + authName +
                "& authToken=" + authToken +
                "& smsPhone=" + smsPhone +
                "& smsTitle=" + smsTitle +
                "& smsContent=" + smsContent;
    }
}
