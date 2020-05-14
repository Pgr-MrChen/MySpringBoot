package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.dao.ShopInfoUsersDao;
import com.cxd.myspringboot.dto.ShopUserDTO;
import com.cxd.myspringboot.entity.ShopInfoUsers;
import com.cxd.myspringboot.entity.ShopUserToken;
import com.cxd.myspringboot.service.ShopUsersService;
import com.cxd.myspringboot.service.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserTokenServiceImplTest {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private ShopUsersService shopUsersService;

    @Autowired
    private ShopInfoUsersDao shopInfoUsersDao;

    @Test
    void createToken() {
        ShopUserDTO shopUserDTO = new ShopUserDTO();
        shopUserDTO.setUsername("test001");
        shopUserDTO.setTelephone("15111888341");
        shopUserDTO.setPassword("huke123");

        //判断用户名是否存在
        if (shopInfoUsersDao.findByUsername(shopUserDTO.getUsername()) != null) {
            log.info("【用户注册】 用户已存在。shopInfoUser={}", shopUserDTO);
        }
        log.info("开始注册用户信息");
        //创建商户信息
        ShopInfoUsers shopInfoUsers = shopUsersService.createShopUserByPwd(shopUserDTO);

        log.info("开始创建用户token表");
        //创建token
        ShopUserToken result = userTokenService.createToken(shopInfoUsers);
        Assert.assertNotNull(result);
    }

    @Test
    void updateToken() {
        ShopInfoUsers shopInfoUsers = shopInfoUsersDao.findByUsername("test001");
        ShopUserToken result = userTokenService.updateToken(shopInfoUsers);
        Assert.assertNotNull(result);

    }
}