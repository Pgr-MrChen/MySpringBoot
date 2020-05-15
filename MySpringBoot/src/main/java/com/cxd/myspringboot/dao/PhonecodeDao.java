package com.cxd.myspringboot.dao;

import com.cxd.myspringboot.entity.Phonecode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商户短信验证码数据库操作
 */
public interface PhonecodeDao extends JpaRepository<Phonecode, Integer> {
    //通过电话查询信息
    Phonecode findByTelephone(String telephone);
}
