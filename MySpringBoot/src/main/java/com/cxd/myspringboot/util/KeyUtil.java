package com.cxd.myspringboot.util;

import java.util.Random;

/**
 *获取随机数，随机昵称，密码盐，验证码等。
 *
 */

public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     *
     * @return
     */

    //随机获取Integer的id
    public static synchronized Integer getIntId() {
        Random random = new Random();
        Integer id = random.nextInt(900000) + 100000;
        return id;
    }

    //获取String类型的id或者账户名
    public static synchronized String getUsername() {
        Random random = new Random();
        Integer username = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(username);
    }

    //获取String的昵称
    public static synchronized String getNickname() {
        Random random = new Random();
        Integer nickname = random.nextInt(900000) + 100000;
        return "Unarke" + String.valueOf(nickname);
    }

    //获取密码盐
    public static synchronized String getSalt() {
        Random random = new Random();
        Integer username = random.nextInt(90000000) + 10000000;
        return System.currentTimeMillis() + String.valueOf(username);
    }

    //获取短信验证码
    public static String getCaptcha() {
        int code = new Random().nextInt(9000) + 1000;
        return "" + code;
    }
}
