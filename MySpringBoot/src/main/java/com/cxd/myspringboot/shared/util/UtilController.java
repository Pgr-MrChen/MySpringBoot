package com.cxd.myspringboot.shared.util;

import com.cxd.myspringboot.shared.dto.resultful.ResultDTO;
import com.cxd.myspringboot.shared.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author GerryMC
 * @Date: 2020/5/19 0019 17:22
 */

@RestController
@RequestMapping("/util")
public class UtilController {
    @Autowired
    private MailService mailService;

    /**
     * 获取ip
     * @param request
     * @return
     */
    @GetMapping("/getIP")
    public String getIPAdr(HttpServletRequest request) {
        String ipAddr = IPUtil.getIpAddr(request);
        System.out.println(ipAddr);
        return "你已被我获取ip地址，请注意人身安全。";
    }


    /**
     * 邮件发送验证
     * @param email
     * @param type
     * @return
     */
    @PostMapping("/emailsms")
    public ResultDTO emailSms(@RequestParam("email") String email,
                              @RequestParam("type") Integer type) {
        if (type == 1) {
            //发送普通邮件(用于登录、注册时发送验证码)

        }
        if (type == 2) {
            //发送html邮件(用于找回密码等操作)
        }
        return ResultDTO.success();
    }

    /**
     * 手机发送验证
     * @param phone
     * @param temp
     * @return
     */
    @PostMapping("/phonesms")
    public ResultDTO phoneSms(@RequestParam("phone") String phone,
                              @RequestParam("temp") Integer temp) {
        if (temp == 1) {
            //发送普通邮件(用于登录、注册时发送验证码)
        }
        if (temp == 2) {
            //发送html邮件(用于找回密码等操作)
        }
        return ResultDTO.success();
    }

}
