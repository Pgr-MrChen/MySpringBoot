package com.cxd.myspringboot.shared.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 角色类
 *
 * @Author GerryMC
 * @Date: 2020/5/15 0015 11:12
 */

@Entity
@Data
public class RoleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //角色名称
    private String name;

    //备注
    private String description;
}
