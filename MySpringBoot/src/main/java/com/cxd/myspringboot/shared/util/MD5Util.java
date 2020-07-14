package com.cxd.myspringboot.shared.util;

import org.springframework.util.DigestUtils;

/**
 * MD5加密
 */
public class MD5Util {

    public static String getMD5(String s1, String s2) {
        String base = s1 + "/unarke" + s2;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    //加密密码
    public static String pwdMD5(String password, String salt) {
        return getMD5(password, salt);
    }

    //Token码生成
    public static String tokenMD5(String username) {
        return getMD5(KeyUtil.getSalt(), username);
    }
}
