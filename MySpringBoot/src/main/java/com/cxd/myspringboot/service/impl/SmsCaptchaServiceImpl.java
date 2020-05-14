package com.cxd.myspringboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cxd.myspringboot.dao.ShopTmpUserscodeDao;
import com.cxd.myspringboot.entity.ShopTmpUserscode;
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
    private ShopTmpUserscodeDao shopTmpUserscodeDao;

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
        if (resCode != 200) {
            log.error("【发送短信】发送短信错误");
            return resCode;
        } else {
            ShopTmpUserscode shopTmpUserscode = shopTmpUserscodeDao.findByTelephone(telephone);
            shopTmpUserscode.setCode(code);
            shopTmpUserscodeDao.save(shopTmpUserscode);
            return resCode;
        }

    }

    //创建短信验证码关系表
    @Override
    @Transactional
    public ShopTmpUserscode createSmsCaptcha(String telephone) {
        //判断电话是否已经存在
        ShopTmpUserscode shopTmpUserscode = shopTmpUserscodeDao.findByTelephone(telephone);
        if (shopTmpUserscode != null) {
            return shopTmpUserscode;

        } else {
            ShopTmpUserscode shopTmpUserscode1 = new ShopTmpUserscode();
            shopTmpUserscode1.setTelephone(telephone);
            ShopTmpUserscode result = shopTmpUserscodeDao.save(shopTmpUserscode1);
            if (result == null) {
                log.error("【创建验证码】创建短信验证码表失败。telephone={}", telephone);
                throw new MySpringBootException(ResultEnum.SHOPUSER_CREATE_CODE_ERROR);
            }
            return result;
        }
    }


}
