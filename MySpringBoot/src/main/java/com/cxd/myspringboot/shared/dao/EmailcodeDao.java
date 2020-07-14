package com.cxd.myspringboot.shared.dao;

import com.cxd.myspringboot.shared.entity.Emailcode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author GerryMC
 * @Date: 2020/7/7 0007 9:32
 *
 * 用户邮箱验证数据库操作
 */
public interface EmailcodeDao extends JpaRepository<Emailcode, Long> {
    //通过邮箱查询
    Emailcode findByEmail(String email);


}
