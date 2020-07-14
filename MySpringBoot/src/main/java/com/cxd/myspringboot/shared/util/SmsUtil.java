package com.cxd.myspringboot.shared.util;

import com.cxd.myspringboot.shared.dto.MsgApiDTO;


/**
 * 发送短信类（自用短信）
 *
 */
public class SmsUtil {

    //发送单条短信
    public static String sendMessage(String telephone, String code) {
        String url = "http://xyb.cqdd.cq.cn/api/sms/sendsms.php";
        String authName = "GerryMC";
        String authToken = "60c811af6328783ad1d05b1db4519268";
        String smsTitle = "验证码";
        String smsContent = "您的验证码是:";
        MsgApiDTO msgApiDTO = new MsgApiDTO(authName, authToken, telephone, smsTitle, smsContent + code);
        String result = DoRequestUtil.sendPost(url, msgApiDTO.getParams());
        return result;
    }

    //查询短信剩余数
    public static String smsCount() {
        String url = "http://xyb.cqdd.cq.cn/api/sms/smscount.php";
        String authName = "GerryMC";
        String authToken = "60c811af6328783ad1d05b1db4519268";
        String params = "authName=" + authName +
                "& authToken=" + authToken;
        String result = DoRequestUtil.sendPost(url, params);
        return result;
    }
}
