package com.cxd.myspringboot.shared.entity;

import com.cxd.myspringboot.shared.enums.IsAuthEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户实体类
 */
@Entity
@Data
public class UserInfo {
    @Id
    private Long id;

    private String email;

    private String telephone;

    //昵称
    private String nickname;

    private Integer roleId;

    private String sex;

    private String birth;

    //密码盐
    private String salt;

    private String password;

    //是否身份认证，默认为0未认证
    private Integer isauth = IsAuthEnum.NO_AUTH.getCode();

    //token码
    private String token;

    //token码过期时间
    private String tokenTimeout;

}
