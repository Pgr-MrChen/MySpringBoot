package com.cxd.myspringboot.service;

import com.cxd.myspringboot.dao.ShopInfoUsersDao;
import com.cxd.myspringboot.dto.ShopUserDTO;
import com.cxd.myspringboot.entity.ShopInfoUsers;
import com.cxd.myspringboot.util.KeyUtil;
import com.cxd.myspringboot.util.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopInfoUsersServiceTest {

    @Autowired
    private ShopUsersService shopUsersService;

    @Autowired
    private ShopInfoUsersDao shopInfoUsersDao;

    @Test
    public void createTest() {
        ShopUserDTO shopUserDTO = new ShopUserDTO();
        shopUserDTO.setUsername("Amos002");
        shopUserDTO.setNickname("珂大妹");
        shopUserDTO.setPassword("123456");
        shopUserDTO.setTelephone("15111888341");
        ShopInfoUsers result = shopUsersService.createShopUserByPwd(shopUserDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest() {
        ShopUserDTO shopUserDTO = new ShopUserDTO();
        shopUserDTO.setToken(MD5Util.getMD5("JJ001", "sj"));
        shopUserDTO.setUsername("JJ001");
        shopUserDTO.setNickname(KeyUtil.getNickname());
        shopUserDTO.setPassword(MD5Util.getMD5("1234567", ""));
        ShopInfoUsers updateResult = shopUsersService.updateShopUser(shopUserDTO);
        Assert.assertNotNull(updateResult);
    }

    @Test
    public void findOne() {
        ShopInfoUsers shopInfoUsers = shopInfoUsersDao.findByUsername("test001");
        System.out.println(shopInfoUsers.toString());
    }

    @Test
    public void findByUsername() {
        ShopInfoUsers result = shopInfoUsersDao.findByUsername("JJ001");
        Assert.assertNotNull(result);
        System.out.println(result.toString());
    }


}