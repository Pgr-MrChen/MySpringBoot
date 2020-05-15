package com.cxd.myspringboot.service;

import com.cxd.myspringboot.entity.Phonecode;

/**
 * 短信验证码
 */
public interface SmsCaptchaService {
    //发送验证码
    Integer sendMsg(String telephone);

    //创建新用户
    Phonecode createSmsCaptcha(String telephone);

}
