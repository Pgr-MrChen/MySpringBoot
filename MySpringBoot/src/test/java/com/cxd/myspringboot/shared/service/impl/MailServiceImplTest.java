package com.cxd.myspringboot.shared.service.impl;

import com.cxd.myspringboot.shared.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() {
        String to = "757869100@qq.com";
        String subject = "测试";
        String content = "测试通过。";
        String result = mailService.sendSimpleMail(to, subject, content);
        if (result.equals("1")) {
            log.info("发送邮件成功");
        }
        Assert.assertEquals("1", result);
    }

    @Test
    public void sendHtmlMail() {
        String to = "757869100@qq.com";
        String subject = "测试";
        String content = "<html>" +
                "<body>" +
                "<h1>Hello，world！</h1>" +
                "</body>" +
                "</html>";
        String result = mailService.sendSimpleMail(to, subject, content);
        if (result.equals("1")) {
            log.info("发送邮件成功");
        }
        Assert.assertEquals("1", result);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String filePath = "D://ysyy.log";
        String result = mailService.sendAttachmentsMail("amoshuke@qq.com", "测试", "这是一封带附件的邮件", filePath);
        if (result.equals("1")) {
            log.info("发送邮件成功");
        }
        Assert.assertEquals("1", result);
    }

    @Test
    public void sendInlineResourceMailTest() throws MessagingException {
        String imgPath = "D://1.jpg";
        String rscId = "test001";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\'> </img></body></html>";
        String result = mailService.sendInlineResourceMail("757869100@qq.com", "测试", content, imgPath, rscId);
        if (result.equals("1")) {
            log.info("发送邮件成功");
        }
        Assert.assertEquals("1", result);
    }

    @Test
    public void sendTemplateMailTest() throws MessagingException {
        Context context = new Context();
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("757869100@qq.com", "测试", emailContent);
    }
}