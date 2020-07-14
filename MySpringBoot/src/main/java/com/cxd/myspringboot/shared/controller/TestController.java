package com.cxd.myspringboot.shared.controller;

import com.cxd.myspringboot.shared.dao.UserInfoDao;
import com.cxd.myspringboot.shared.dao.PhonecodeDao;
import com.cxd.myspringboot.shared.dto.resultful.CodeMsgDTO;
import com.cxd.myspringboot.shared.dto.resultful.ResultDTO;
import com.cxd.myspringboot.shared.dto.UserDTO;
import com.cxd.myspringboot.shared.entity.UserInfo;
import com.cxd.myspringboot.shared.entity.Phonecode;

import com.cxd.myspringboot.shared.service.UserService;
import com.cxd.myspringboot.shared.service.PhoneSmsService;
import com.cxd.myspringboot.shared.util.MD5Util;
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
    private UserInfoDao userInfoDao;

    @Autowired
    private PhonecodeDao phonecodeDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneSmsService phoneSmsService;

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
        Phonecode phonecode = phonecodeDao.findByTelephone(telephone);
        String yzm = phonecode.getCode();
        if (yzm.equals(code)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setPassword(pwd);
            userDTO.setTelephone(telephone);

            //判断用户名是否存在
            if (userInfoDao.findByUsername(userDTO.getUsername()) != null) {
                log.info("【用户注册】 用户已存在。shopInfoUser={}", userDTO);
                return ResultDTO.success(CodeMsgDTO.USER_EXIST);
            }
            log.info("开始注册用户信息");
            //创建商户信息
            UserInfo userInfo = userService.createShopUserByPwd(userDTO);

            log.info("开始创建用户token表");
            //创建token
            userTokenService.createToken(userInfo);
            return ResultDTO.success();
        } else {
            System.out.println("验证码错误");
            return ResultDTO.error(CodeMsgDTO.VER_CODE_ERROR);
        }

    }

  /*  //手机号注册
    @PostMapping("/register/msgRegister")
    public ResultDTO register(@RequestParam("telephone") String telephone){
        //判断用户名是否存在
        if (userInfoDao.findByTelephone(telephone) != null){
            log.info("【用户注册】 用户已存在。telephone={}",telephone);
            return new ResultDTO(101, "用户已存在", new String(""));
        }


        //创建商户信息表
        UserInfo shopInfoUsers = shopInfoUsersService.createShopUserBySms(telephone);

        //创建token
        userTokenService.createToken(shopInfoUsers);
        return new ResultDTO(102, "用户创建成功", new String(""));
    }*/

    //验证账户密码登录
    @PostMapping("/pwdLogin")
    public ResultDTO pwdLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        //查询账号是否存在
        UserInfo userInfo = userInfoDao.findByUsername(username);
        if (userInfo != null) {
            //判断密码是否正确
            if (userInfo.getPassword().equals(MD5Util.pwdMD5(password, userInfo.getSalt()))) {
                ShopUserToken shopUserToken = userTokenService.updateToken(userInfo);
                String data = shopUserToken.getToken();
                return new ResultDTO(200, "成功", data);
            } else {
                return new ResultDTO(201, "密码错误", new String(""));
            }
        } else {
            log.info("账号不存在");
            return ResultDTO.error(CodeMsgDTO.USER_NOT_EXIST);
        }

    }

    //验证短信码登录
    @PostMapping(value = "/msgLogin")
    public ResultDTO msgLogin(@RequestParam("telephone") String telephone,
                              @RequestParam("code") String code) {
        Phonecode phonecode = phonecodeDao.findByTelephone(telephone);
        if (phonecode != null) {
            String yzm = phonecode.getCode();
            if (yzm.equals(code)) {
                UserInfo userInfo = userInfoDao.findByTelephone(telephone);
                ShopUserToken shopUserToken = userTokenService.updateToken(userInfo);
                String data = shopUserToken.getToken();
                ResultDTO resultDTO = new ResultDTO(200, "成功", data);
                return resultDTO;
            } else {
                System.out.println("验证码错误");
                return ResultDTO.error(CodeMsgDTO.VER_CODE_ERROR);
            }
        } else {
            System.out.println("该手机号并未注册");
            return ResultDTO.error(CodeMsgDTO.USER_NOT_EXIST);
        }

    }

    //登陆发送短信码
    @PostMapping("/smsLogin")
    public ResultDTO smsLogin(@RequestParam("telephone") String telephone) {
        //查询账户是否存在
        UserInfo userInfo = userInfoDao.findByTelephone(telephone);
        if (userInfo != null) {   //如果存在直接发送验证码
            phoneSmsService.sendMsg(telephone);
            return ResultDTO.success();
        } else {
            System.out.println("该手机号并未注册");
            return ResultDTO.error(CodeMsgDTO.USER_NOT_EXIST);
        }

    }

    //注册发送验证码
    @PostMapping("/smsRegister")
    public ResultDTO smsRegister(@RequestParam("telephone") String telephone) {
        //判断电话是否已经注册
        UserInfo userInfo = userInfoDao.findByTelephone(telephone);
        if (userInfo == null) {
            //新建电话验证码
            phoneSmsService.createSmsCaptcha(telephone);

            //发送短信并存验证码
            Integer code = phoneSmsService.sendMsg(telephone);
            if (code != 200) {
                return ResultDTO.error(CodeMsgDTO.SMS_ERROR);
            }
            return ResultDTO.success();
        }

        return ResultDTO.error(CodeMsgDTO.PHONE_EXIST);
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
