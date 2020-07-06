package com.cxd.myspringboot.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 短信验证码
 */
@Entity
@Data
public class Phonecode {
    @Id
    private Long id;

    private String telephone;

    //短信验证码
    private String code;

    //验证码过期时间
    private String timeout;

    public Phonecode() {
    }
}
