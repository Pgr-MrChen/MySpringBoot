package com.cxd.myspringboot.controller.util;

import com.cxd.myspringboot.util.IPUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author GerryMC
 * @Date: 2020/5/13 0013 14:46
 */

@RestController
@RequestMapping("/util")
public class UtilController {

    @GetMapping("/getIP")
    public String getIPAdr(HttpServletRequest request) {
        String ipAddr = IPUtil.getIpAddr(request);
        System.out.println(ipAddr);
        return "你已被我获取ip地址，请注意人身安全。";
    }

}
