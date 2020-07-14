package com.cxd.myspringboot.shared.dto;

import com.cxd.myspringboot.shared.enums.IsAuthEnum;
import lombok.Data;

@Data
public class UserDTO {
    //用户token码
    private String token;

    //账号
    private String username;

    //昵称
    private String nickname;

    private Integer roleId;

    private String email;

    private String telephone;

    private String sex;

    private String birth;

    private String password;

    //是否身份认证，默认为0未认证
    private Integer isauth = IsAuthEnum.NO_AUTH.getCode();
}
