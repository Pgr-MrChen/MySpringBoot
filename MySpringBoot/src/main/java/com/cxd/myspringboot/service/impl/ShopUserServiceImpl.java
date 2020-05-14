package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.dao.ShopInfoUsersDao;
import com.cxd.myspringboot.dao.ShopUserTokenDao;
import com.cxd.myspringboot.dto.ShopUserDTO;
import com.cxd.myspringboot.entity.ShopInfoUsers;
import com.cxd.myspringboot.enums.ResultEnum;
import com.cxd.myspringboot.exception.MySpringBootException;
import com.cxd.myspringboot.service.ShopUsersService;
import com.cxd.myspringboot.util.KeyUtil;
import com.cxd.myspringboot.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class ShopUserServiceImpl implements ShopUsersService {
    @Autowired
    private ShopInfoUsersDao shopInfoUsersDao;

    @Autowired
    private ShopUserTokenDao shopUserTokenDao;

    //密码用户注册
    @Override
    @Transactional
    public ShopInfoUsers createShopUserByPwd(ShopUserDTO shopUserDTO) {
        ShopInfoUsers shopInfoUser = new ShopInfoUsers();
        BeanUtils.copyProperties(shopUserDTO, shopInfoUser);
        shopInfoUser.setId(KeyUtil.getIntId());
        shopInfoUser.setSalt(KeyUtil.getSalt());
        shopInfoUser.setNickname(KeyUtil.getNickname());
        shopInfoUser.setPassword(MD5Util.pwdMD5(shopUserDTO.getPassword(), shopInfoUser.getSalt()));
        ShopInfoUsers result = shopInfoUsersDao.save(shopInfoUser);
        if (result == null) {
            log.error("【用户注册】账户密码创建用户失败，shopUserDTO={}", shopUserDTO);
            throw new MySpringBootException(ResultEnum.SHOPUSER_CREATE_PWD_ERROR);
        }
        return result;
    }

    //短信注册
    @Override
    @Transactional
    public ShopInfoUsers createShopUserBySms(String telephone) {
        ShopInfoUsers shopInfoUsers = new ShopInfoUsers();
        shopInfoUsers.setId(KeyUtil.getIntId());
        shopInfoUsers.setUsername(KeyUtil.getUsername());
        shopInfoUsers.setNickname(KeyUtil.getNickname());
        shopInfoUsers.setSalt(KeyUtil.getSalt());
        shopInfoUsers.setPassword(MD5Util.pwdMD5("123456", shopInfoUsers.getSalt()));//默认密码为123456
        ShopInfoUsers result = shopInfoUsersDao.save(shopInfoUsers);
        if (result == null) {
            log.error("【用户注册】短信创建用户失败，telephone={}", telephone);
            throw new MySpringBootException(ResultEnum.SHOPUSER_CREATE_SMS_ERROR);
        }
        return result;
    }

    //修改用户信息
    @Override
    @Transactional
    public ShopInfoUsers updateShopUser(ShopUserDTO shopUserDTO) {
        ShopInfoUsers shopInfoUsers = shopInfoUsersDao.findByUsername(shopUserDTO.getUsername());
        BeanUtils.copyProperties(shopUserDTO, shopInfoUsers);
        shopInfoUsers.setId(shopUserTokenDao.findByToken(shopUserDTO.getToken()).getId());
        ShopInfoUsers result = shopInfoUsersDao.save(shopInfoUsers);
        if (result == null) {
            log.error("【用户修改信息】商品用户修改信息失败，shopUserDTO={}", shopUserDTO);
            throw new MySpringBootException(ResultEnum.SHOPUSER_UPDATE_ERROR);
        }
        return result;
    }


}
