package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.dao.ShopTmpUserscodeDao;
import com.cxd.myspringboot.entity.ShopTmpUserscode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CaptchaServiceImplTest {

    @Autowired
    private ShopTmpUserscodeDao shopTmpUserscodeDao;


    @Test
    void updateCode() {
        ShopTmpUserscode shopTmpUserscode = shopTmpUserscodeDao.findByTelephone("15111888341");
        shopTmpUserscode.setCode("1234");
        shopTmpUserscodeDao.save(shopTmpUserscode);
    }
}