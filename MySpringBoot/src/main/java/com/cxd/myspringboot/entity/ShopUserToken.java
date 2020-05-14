package com.cxd.myspringboot.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 商品用户token码
 */
@Entity
@Data
public class ShopUserToken {
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //token码
    private String token;

    //商品用户id
    private Integer shopUserId;

}
