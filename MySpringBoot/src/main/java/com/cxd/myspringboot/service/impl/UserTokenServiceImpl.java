package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.dao.ShopUserTokenDao;
import com.cxd.myspringboot.entity.ShopInfoUsers;
import com.cxd.myspringboot.entity.ShopUserToken;
import com.cxd.myspringboot.enums.ResultEnum;
import com.cxd.myspringboot.exception.MySpringBootException;
import com.cxd.myspringboot.service.UserTokenService;
import com.cxd.myspringboot.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商户token码
 */
@Service
@Slf4j
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private ShopUserTokenDao shopUserTokenDao;


    //新建token码。
    @Override
    @Transactional
    public ShopUserToken createToken(ShopInfoUsers shopInfoUsers) {
        ShopUserToken shopUserToken = new ShopUserToken();
        shopUserToken.setShopUserId(shopInfoUsers.getId());
        ShopUserToken result = shopUserTokenDao.save(shopUserToken);
        if (result == null) {
            log.error("【新建token】 新建token码失败 shopInfoUsers={}", shopInfoUsers);
            throw new MySpringBootException(ResultEnum.SHOPUSER_CREATE_TOKEN_ERROR);
        }
        return result;
    }

    //更新token码
    @Override
    @Transactional
    public ShopUserToken updateToken(ShopInfoUsers shopInfoUsers) {
        ShopUserToken shopUserToken = shopUserTokenDao.findByShopUserId(shopInfoUsers.getId());
        shopUserToken.setToken(MD5Util.tokenMD5(shopInfoUsers.getUsername()));
        ShopUserToken updateResult = shopUserTokenDao.save(shopUserToken);
        if (updateResult == null) {
            log.error("【更新token】 商户更新token码失败 shopInfoUsers={}", shopInfoUsers);
            throw new MySpringBootException(ResultEnum.SHOPUSER_UPDATE_TOKEN_ERROR);
        }
        return updateResult;
    }


}
