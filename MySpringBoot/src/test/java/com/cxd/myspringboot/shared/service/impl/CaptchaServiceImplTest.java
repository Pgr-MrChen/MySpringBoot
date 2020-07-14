package com.cxd.myspringboot.shared.service.impl;

import com.cxd.myspringboot.shared.dao.PhonecodeDao;
import com.cxd.myspringboot.shared.entity.Phonecode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CaptchaServiceImplTest {

    @Autowired
    private PhonecodeDao phonecodeDao;


    @Test
    void updateCode() {
        Phonecode phonecode = phonecodeDao.findByTelephone("15111888341");
        phonecode.setCode("1234");
        phonecodeDao.save(phonecode);
    }
}