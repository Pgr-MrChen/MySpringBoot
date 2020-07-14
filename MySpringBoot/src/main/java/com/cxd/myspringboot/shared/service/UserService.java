package com.cxd.myspringboot.shared.service;

import com.cxd.myspringboot.shared.dto.resultful.ResultDTO;
import com.cxd.myspringboot.shared.dto.UserDTO;
import com.cxd.myspringboot.shared.entity.UserInfo;

/**
 * 用户操作
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
