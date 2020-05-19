package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.dao.UserInfoDao;
import com.cxd.myspringboot.dto.CodeMsgDTO;
import com.cxd.myspringboot.dto.ResultDTO;
import com.cxd.myspringboot.dto.UserDTO;
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

    //用户注册
    @Override
    public ResultDTO register(UserInfo userInfo) {
        userInfo.setId(SnowFlakeUtil.getSnowFlakeId());
        userInfo.setRoleId(userInfo.getRoleId());
        String salt = KeyUtil.getSalt();  //获取唯一密码盐
        userInfo.setSalt(salt);
        userInfo.setPassword(MD5Util.pwdMD5(userInfo.getPassword(), salt));
        UserInfo result = userInfoDao.save(userInfo);
        if (result == null) {
            log.error("【用户注册】用户注册失败，userInfo={}", result);
            throw new MySpringBootException(ResultEnum.SYSTEM_DATA_SAVE_ERROR);
        }
        return ResultDTO.success();
    }

    //密码用户注册
    @Override
    @Transactional
    public ResultDTO addUserByPwd(UserDTO userDTO) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDTO, userInfo);
        userInfo.setId(SnowFlakeUtil.getSnowFlakeId());
        userInfo.setRoleId(userDTO.getRoleId());
        userInfo.setNickname(KeyUtil.getNickname());
        String salt = KeyUtil.getSalt();  //获取唯一密码盐
        userInfo.setSalt(salt);
        userInfo.setNickname(KeyUtil.getNickname());
        userInfo.setPassword(MD5Util.pwdMD5(userDTO.getPassword(), salt));
        UserInfo result = userInfoDao.save(userInfo);
        if (result == null) {
            log.error("【用户注册】创建用户失败，userDTO={}", userDTO);
            throw new MySpringBootException(ResultEnum.USER_CREATE_ERROR);
        }
        return ResultDTO.success();
    }

    //短信注册
    @Override
    @Transactional
    public ResultDTO addUserBySms(String telephone) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(SnowFlakeUtil.getSnowFlakeId());
        //userInfo.setRoleId(userInfo.getRoleId());  todo 设置角色
        userInfo.setNickname(KeyUtil.getNickname());
        userInfo.setSalt(KeyUtil.getSalt());
        userInfo.setPassword(MD5Util.pwdMD5("123456", userInfo.getSalt()));//默认密码为123456
        UserInfo result = userInfoDao.save(userInfo);
        if (result == null) {
            log.error("【用户注册】短信创建用户失败，telephone={}", telephone);
            throw new MySpringBootException(ResultEnum.USER_CREATE_ERROR);
        }
        return ResultDTO.success();
    }

    //修改用户信息
    @Override
    @Transactional
    public ResultDTO updateUser(UserDTO userDTO) {
        UserInfo userInfo = userInfoDao.findByUsername(userDTO.getUsername());
        BeanUtils.copyProperties(userDTO, userInfo);
        userInfo.setId(shopUserTokenDao.findByToken(userDTO.getToken()).getId());
        UserInfo result = userInfoDao.save(userInfo);
        if (result == null) {
            log.error("【用户修改信息】商品用户修改信息失败，userDTO={}", userDTO);
            throw new MySpringBootException(ResultEnum.SHOPUSER_UPDATE_ERROR);
        }
        return ResultDTO.success();
    }


}
