package com.cxd.myspringboot.shared.dao;

import com.cxd.myspringboot.shared.entity.Phonecode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户短信验证码数据库操作
 */
public interface PhonecodeDao extends JpaRepository<Phonecode, Long> {
    //通过电话查询信息
    Phonecode findByTelephone(String telephone);
}
