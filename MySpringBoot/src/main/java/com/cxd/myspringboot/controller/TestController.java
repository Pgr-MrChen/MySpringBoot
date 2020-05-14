package com.cxd.myspringboot.controller;

import com.cxd.myspringboot.dao.ShopInfoUsersDao;
import com.cxd.myspringboot.dao.ShopTmpUserscodeDao;
import com.cxd.myspringboot.dto.ResultDTO;
import com.cxd.myspringboot.dto.ShopUserDTO;
import com.cxd.myspringboot.entity.ShopInfoUsers;
import com.cxd.myspringboot.entity.ShopTmpUserscode;

import com.cxd.myspringboot.entity.ShopUserToken;
import com.cxd.myspringboot.service.ShopUsersService;
import com.cxd.myspringboot.service.SmsCaptchaService;
import com.cxd.myspringboot.service.impl.UserTokenServiceImpl;
import com.cxd.myspringboot.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/demo")
public class TestController {
    @Autowired
    private ShopInfoUsersDao shopInfoUsersDao;

    @Autowired
    private ShopTmpUserscodeDao shopTmpUserscodeDao;

    @Autowired
    private ShopUsersService shopUsersService;

    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @Autowired
    private UserTokenServiceImpl userTokenService;

    @Value(value = "${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    //注册
    @PostMapping("/register")
    public ResultDTO register(@RequestParam("username") String username,
                              @RequestParam("password") String pwd,
                              @RequestParam("telephone") String telephone,
                              @RequestParam("captcha") String code) {
        //查询验证码是否正确
        ShopTmpUserscode shopTmpUserscode = shopTmpUserscodeDao.findByTelephone(telephone);
        String yzm = shopTmpUserscode.getCode();
        if (yzm.equals(code)) {
            ShopUserDTO shopUserDTO = new ShopUserDTO();
            shopUserDTO.setUsername(username);
            shopUserDTO.setPassword(pwd);
            shopUserDTO.setTelephone(telephone);

            //判断用户名是否存在
            if (shopInfoUsersDao.findByUsername(shopUserDTO.getUsername()) != null) {
                log.info("【用户注册】 用户已存在。shopInfoUser={}", shopUserDTO);
                return new ResultDTO(101, "用户已存在", new String(""));
            }
            log.info("开始注册用户信息");
            //创建商户信息
            ShopInfoUsers shopInfoUsers = shopUsersService.createShopUserByPwd(shopUserDTO);

            log.info("开始创建用户token表");
            //创建token
            userTokenService.createToken(shopInfoUsers);
            return new ResultDTO(102, "用户创建成功", new String(""));
        } else {
            System.out.println("验证码错误");
            ResultDTO resultDTO = new ResultDTO(500, "验证码错误", new String(""));
            return resultDTO;
        }

    }

  /*  //手机号注册
    @PostMapping("/register/msgRegister")
    public ResultDTO register(@RequestParam("telephone") String telephone){
        //判断用户名是否存在
        if (shopInfoUsersDao.findByTelephone(telephone) != null){
            log.info("【用户注册】 用户已存在。telephone={}",telephone);
            return new ResultDTO(101, "用户已存在", new String(""));
        }


        //创建商户信息表
        ShopInfoUsers shopInfoUsers = shopInfoUsersService.createShopUserBySms(telephone);

        //创建token
        userTokenService.createToken(shopInfoUsers);
        return new ResultDTO(102, "用户创建成功", new String(""));
    }*/

    //验证账户密码登录
    @PostMapping("/pwdLogin")
    public ResultDTO pwdLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        //查询账号是否存在
        ShopInfoUsers shopInfoUsers = shopInfoUsersDao.findByUsername(username);
        if (shopInfoUsers != null) {
            //判断密码是否正确
            if (shopInfoUsers.getPassword().equals(MD5Util.pwdMD5(password, shopInfoUsers.getSalt()))) {
                ShopUserToken shopUserToken = userTokenService.updateToken(shopInfoUsers);
                String data = shopUserToken.getToken();
                return new ResultDTO(200, "成功", data);
            } else {
                return new ResultDTO(201, "密码错误", new String(""));
            }
        } else {
            log.info("账号不存在");
            return new ResultDTO(202, "账户不存在", new String(""));
        }

    }

    //验证短信码登录
    @PostMapping(value = "/msgLogin")
    public ResultDTO msgLogin(@RequestParam("telephone") String telephone,
                              @RequestParam("code") String code) {
        ShopTmpUserscode shopTmpUserscode = shopTmpUserscodeDao.findByTelephone(telephone);
        if (shopTmpUserscode != null) {
            String yzm = shopTmpUserscode.getCode();
            if (yzm.equals(code)) {
                ShopInfoUsers shopInfoUsers = shopInfoUsersDao.findByTelephone(telephone);
                ShopUserToken shopUserToken = userTokenService.updateToken(shopInfoUsers);
                String data = shopUserToken.getToken();
                ResultDTO resultDTO = new ResultDTO(200, "成功", data);
                return resultDTO;
            } else {
                System.out.println("验证码错误");
                ResultDTO resultDTO = new ResultDTO(501, "验证码错误", new String(""));
                return resultDTO;
            }
        } else {
            System.out.println("该手机号并未注册");
            ResultDTO resultDTO = new ResultDTO(500, "账户不存在", new String(""));
            return resultDTO;
        }


    }

    //登陆发送短信码
    @PostMapping("/smsLogin")
    public ResultDTO smsLogin(@RequestParam("telephone") String telephone) {
        //查询账户是否存在
        ShopInfoUsers shopInfoUsers = shopInfoUsersDao.findByTelephone(telephone);
        if (shopInfoUsers != null) {   //如果存在直接发送验证码
            smsCaptchaService.sendMsg(telephone);
            ResultDTO resultDTO = new ResultDTO(200, "成功", new String(""));
            return resultDTO;
        } else {
            System.out.println("该手机号并未注册");
            ResultDTO resultDTO = new ResultDTO(500, "账户不存在", new String(""));
            return resultDTO;
        }

    }

    //注册发送验证码
    @PostMapping("/smsRegister")
    public ResultDTO smsRegister(@RequestParam("telephone") String telephone) {
        //判断电话是否已经注册
        ShopInfoUsers shopInfoUsers = shopInfoUsersDao.findByTelephone(telephone);
        if (shopInfoUsers == null) {
            //新建电话验证码
            smsCaptchaService.createSmsCaptcha(telephone);

            //发送短信并存验证码
            Integer code = smsCaptchaService.sendMsg(telephone);
            if (code != 200) {
                return new ResultDTO(104, "发送短信失败", new String(""));
            }
            return new ResultDTO(200, "发送成功", new String(""));
        }

        return new ResultDTO(502, "手机号已注册", new String(""));
    }


    //发送邮箱
    @GetMapping("/sendMail")
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("320168099@qq.com");
        message.setFrom(from);
        message.setSubject("测试");
        message.setText("content");
        mailSender.send(message);
        return "发送成功";
    }

}