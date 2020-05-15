package com.cxd.myspringboot.service;

import com.cxd.myspringboot.dto.ShopUserDTO;
import com.cxd.myspringboot.entity.UserInfo;

/**
 * 商店用户
 */
public interface UserService {
    //账户密码注册
    UserInfo createUserByPwd(ShopUserDTO shopUserDTO);

    //短信注册
    UserInfo createUserBySms(String telephone);

    //修改用户信息
    UserInfo updateUser(ShopUserDTO shopUserDTO);
}
