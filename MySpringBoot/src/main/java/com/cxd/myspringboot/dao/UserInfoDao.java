package com.cxd.myspringboot.dao;

import com.cxd.myspringboot.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商店用户数据库操作
 */
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

    //通过账号查询
    UserInfo findByUsername(String username);

    //通过电话查询
    UserInfo findByTelephone(String telephone);

    //通过token查询
    UserInfo findByToken(String token);
}
