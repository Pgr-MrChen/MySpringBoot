package com.cxd.myspringboot.dao;

import com.cxd.myspringboot.entity.ShopTmpUserscode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商户短信验证码数据库操作
 */
public interface ShopTmpUserscodeDao extends JpaRepository<ShopTmpUserscode, Integer> {
    //通过电话查询信息
    ShopTmpUserscode findByTelephone(String telephone);
}
