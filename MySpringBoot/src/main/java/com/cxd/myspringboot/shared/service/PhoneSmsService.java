package com.cxd.myspringboot.shared.service;

import com.cxd.myspringboot.shared.dto.resultful.ResultDTO;

/**
 * 短信验证码
 */
public interface PhoneSmsService {
    //发送验证码
    ResultDTO sendMsg(String telephone);

    //验证验证码
    ResultDTO verCode(String telephone, String code);

}
