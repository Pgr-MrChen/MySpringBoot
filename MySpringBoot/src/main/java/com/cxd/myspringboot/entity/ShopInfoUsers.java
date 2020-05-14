package com.cxd.myspringboot.entity;

import com.cxd.myspringboot.enums.IsAuthEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 商店用户实体类
 */
@Entity
@Data
public class ShopInfoUsers {
    @Id
    private Integer id;

    //账号
    private String username;

    //昵称
    private String nickname;

    private String email;

    private String telephone;

    private String sex;

    private String birth;

    //密码盐
    private String salt;

    private String password;

    //是否身份认证，默认为0未认证
    private Integer isauth = IsAuthEnum.NO_AUTH.getCode();

}
