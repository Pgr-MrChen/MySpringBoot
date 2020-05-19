package com.cxd.myspringboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cxd.myspringboot.dao.PhonecodeDao;
import com.cxd.myspringboot.entity.Phonecode;
import com.cxd.myspringboot.enums.ResultEnum;
import com.cxd.myspringboot.exception.MySpringBootException;
import com.cxd.myspringboot.service.SmsCaptchaService;
import com.cxd.myspringboot.util.KeyUtil;
import com.cxd.myspringboot.util.SmsUtil;
import com.alibaba.fastjson.JSON;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class SmsCaptchaServiceImpl implements SmsCaptchaService {
    @Autowired
    private PhonecodeDao phonecodeDao;

    //发送短信验证码
    @Override
    @Transactional
    public Integer sendMsg(String telephone) {
        //获取验证码
        String code = KeyUtil.getCaptcha();

        //发送验证码
        String result = SmsUtil.sendMessage(telephone, code);
        JSONObject json = JSON.parseObject(result);
        Integer resCode = json.getInteger("code");
        if(resCode != 200) {
            log.error("【发送短信】发送短信错误");
            return resCode;
        } else {
            Phonecode phonecode = phonecodeDao.findByTelephone(telephone);
            phonecode.setCode(code);
            phonecodeDao.save(phonecode);
            return resCode;
        }

    }

    //创建短信验证码关系表
    @Override
    @Transactional
    public Phonecode createSmsCaptcha(String telephone) {
        //判断电话是否已经存在
        Phonecode phonecode = phonecodeDao.findByTelephone(telephone);
        if (phonecode != null) {
            return phonecode;

        } else {
            Phonecode phonecode1 = new Phonecode();
            phonecode1.setTelephone(telephone);
            Phonecode result = phonecodeDao.save(phonecode1);
            if (result == null) {
                log.error("【创建验证码】创建短信验证码表失败。telephone={}", telephone);
                throw new MySpringBootException(ResultEnum.SHOPUSER_CREATE_CODE_ERROR);
            }
            return result;
        }
    }


}
