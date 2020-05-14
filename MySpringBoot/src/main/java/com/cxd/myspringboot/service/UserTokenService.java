package com.cxd.myspringboot.service;

import com.cxd.myspringboot.entity.ShopInfoUsers;
import com.cxd.myspringboot.entity.ShopUserToken;

/**
 * 商户token码获取
 */
public interface UserTokenService {
    //新建token码
    ShopUserToken createToken(ShopInfoUsers shopInfoUsers);

    //更新token码
    ShopUserToken updateToken(ShopInfoUsers shopInfoUsers);
}
