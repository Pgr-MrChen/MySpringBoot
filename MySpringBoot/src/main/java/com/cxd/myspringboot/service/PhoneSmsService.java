package com.cxd.myspringboot.service;

import com.cxd.myspringboot.dto.resultful.ResultDTO;
import com.cxd.myspringboot.entity.Phonecode;

/**
 * 短信验证码
 */
public interface PhoneSmsService {
    //发送验证码
    ResultDTO sendMsg(String telephone);

    //验证验证码
    ResultDTO verCode(String telephone, String code);

}
