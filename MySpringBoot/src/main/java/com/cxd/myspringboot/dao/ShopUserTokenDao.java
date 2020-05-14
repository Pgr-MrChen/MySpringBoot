package com.cxd.myspringboot.dao;

import com.cxd.myspringboot.entity.ShopUserToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商户token码数据库操作
 */
public interface ShopUserTokenDao extends JpaRepository<ShopUserToken, Integer> {
    //通过token码查找
    ShopUserToken findByToken(String token);

    //通过商户id查找
    ShopUserToken findByShopUserId(Integer shopUserId);
}
