package com.cxd.myspringboot.service;

import com.cxd.myspringboot.dto.ResultDTO;
import com.cxd.myspringboot.dto.UserDTO;
import com.cxd.myspringboot.entity.UserInfo;

/**
 * 商店用户
 */
public interface UserService {
    //用户注册
    ResultDTO register(UserInfo userInfo);

    //账户密码注册
    ResultDTO addUserByPwd(UserDTO userDTO);

    //手机短信注册
    ResultDTO addUserBySms(String telephone);

    //修改用户信息
    ResultDTO updateUser(UserDTO userDTO);
}
