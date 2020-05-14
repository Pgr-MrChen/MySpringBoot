package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.service.SmsCaptchaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 验证码测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsCaptchaServiceImplTest {
    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @Test
    public void sendMsg() {
        Integer result = smsCaptchaService.sendMsg("17323964195");
        System.out.println(result);
    }

    @Test
    public void createSmsCaptcha() {
        smsCaptchaService.createSmsCaptcha("17323964195");
    }
}