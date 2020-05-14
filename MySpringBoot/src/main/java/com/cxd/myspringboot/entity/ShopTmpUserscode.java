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
public class ShopTmpUserscode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String telephone;

    //短信验证码
    private String code;

    public ShopTmpUserscode() {
    }

    public ShopTmpUserscode(Integer id, String telephone, String code) {
        this.id = id;
        this.telephone = telephone;
        this.code = code;
    }
}
