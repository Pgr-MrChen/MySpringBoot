package com.cxd.myspringboot.dao;

import com.cxd.myspringboot.entity.ShopInfoUsers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商店用户数据库操作
 */
public interface ShopInfoUsersDao extends JpaRepository<ShopInfoUsers, Integer> {

    //通过账号查询
    ShopInfoUsers findByUsername(String username);

    //通过电话查询
    ShopInfoUsers findByTelephone(String telephone);
}
