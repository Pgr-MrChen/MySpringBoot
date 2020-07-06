package com.cxd.myspringboot.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 邮箱验证码
 */
@Entity
@Data
public class Emailcode {
    @Id
    private Long id;

    private String email;

    //短信验证码
    private String code;

    //验证码过期时间
    private String timeout;

    public Emailcode() {
    }
}
