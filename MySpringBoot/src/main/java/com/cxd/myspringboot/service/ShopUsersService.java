package com.cxd.myspringboot.service;

import com.cxd.myspringboot.dto.ShopUserDTO;
import com.cxd.myspringboot.entity.ShopInfoUsers;

/**
 * 商店用户
 */
public interface ShopUsersService {
    //账户密码注册
    ShopInfoUsers createShopUserByPwd(ShopUserDTO shopUserDTO);

    //短信注册
    ShopInfoUsers createShopUserBySms(String telephone);

    //修改用户信息
    ShopInfoUsers updateShopUser(ShopUserDTO shopUserDTO);
}
