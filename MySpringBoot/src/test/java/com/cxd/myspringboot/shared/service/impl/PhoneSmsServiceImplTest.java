package com.cxd.myspringboot.shared.service.impl;

import com.cxd.myspringboot.shared.service.PhoneSmsService;
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
public class PhoneSmsServiceImplTest {
    @Autowired
    private PhoneSmsService phoneSmsService;

    @Test
    public void sendMsg() {
        Integer result = phoneSmsService.sendMsg("17323964195");
        System.out.println(result);
    }

    @Test
    public void createSmsCaptcha() {
        phoneSmsService.createSmsCaptcha("17323964195");
    }
}