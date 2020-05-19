package com.cxd.myspringboot.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author GerryMC
 * @Date: 2020/5/15 0015 11:18
 */

@Entity
@Data
public class MenuInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //菜单名称
    private String name;

    //备注
    private String description;
}
