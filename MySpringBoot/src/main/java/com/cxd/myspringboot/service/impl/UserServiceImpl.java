package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.dao.UserInfoDao;
import com.cxd.myspringboot.dto.ShopUserDTO;
import com.cxd.myspringboot.entity.UserInfo;
import com.cxd.myspringboot.enums.ResultEnum;
import com.cxd.myspringboot.exception.MySpringBootException;
import com.cxd.myspringboot.service.UserService;
import com.cxd.myspringboot.util.KeyUtil;
import com.cxd.myspringboot.util.MD5Util;
import com.cxd.myspringboot.util.SnowFlakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;

    //密码用户注册
    @Override
    @Transactional
    public UserInfo createUserByPwd(ShopUserDTO shopUserDTO) {
        UserInfo shopInfoUserInfo = new UserInfo();
        BeanUtils.copyProperties(shopUserDTO, shopInfoUserInfo);
        shopInfoUserInfo.setId(SnowFlakeUtil.getSnowFlakeId());
        shopInfoUserInfo.setRoleId(shopUserDTO.getRoleId());
        shopInfoUserInfo.setSalt(KeyUtil.getSalt());
        shopInfoUserInfo.setNickname(KeyUtil.getNickname());
        shopInfoUserInfo.setPassword(MD5Util.pwdMD5(shopUserDTO.getPassword(), shopInfoUserInfo.getSalt()));
        UserInfo result = userInfoDao.save(shopInfoUserInfo);
        if (result == null) {
            log.error("【用户注册】账户密码创建用户失败，shopUserDTO={}", shopUserDTO);
            throw new MySpringBootException(ResultEnum.SHOPUSER_CREATE_PWD_ERROR);
        }
        return result;
    }

    //短信注册
    @Override
    @Transactional
    public UserInfo createUserBySms(String telephone) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(SnowFlakeUtil.getSnowFlakeId());
        //userInfo.setRoleId(userInfo.getRoleId());  todo 设置角色
        userInfo.setNickname(KeyUtil.getNickname());
        userInfo.setSalt(KeyUtil.getSalt());
        userInfo.setPassword(MD5Util.pwdMD5("123456", userInfo.getSalt()));//默认密码为123456
        UserInfo result = userInfoDao.save(userInfo);
        if (result == null) {
            log.error("【用户注册】短信创建用户失败，telephone={}", telephone);
            throw new MySpringBootException(ResultEnum.SHOPUSER_CREATE_SMS_ERROR);
        }
        return result;
    }

    //修改用户信息
    @Override
    @Transactional
    public UserInfo updateUser(ShopUserDTO shopUserDTO) {
        UserInfo userInfo = userInfoDao.findByUsername(shopUserDTO.getUsername());
        BeanUtils.copyProperties(shopUserDTO, userInfo);
        userInfo.setId(shopUserTokenDao.findByToken(shopUserDTO.getToken()).getId());
        UserInfo result = userInfoDao.save(userInfo);
        if (result == null) {
            log.error("【用户修改信息】商品用户修改信息失败，shopUserDTO={}", shopUserDTO);
            throw new MySpringBootException(ResultEnum.SHOPUSER_UPDATE_ERROR);
        }
        return result;
    }


}
