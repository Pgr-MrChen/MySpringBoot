package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.dto.CodeMsgDTO;
import com.cxd.myspringboot.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     * @return
     */
    @Override
    public Integer sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("简单邮件已经发送。");
            return CodeMsgDTO.RESULT_SUCCESS.getCode();
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常！", e);
        }
        return CodeMsgDTO.RESULT_FAILED.getCode();
    }

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public Integer sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            log.info("html邮件发送成功");
            return CodeMsgDTO.RESULT_SUCCESS.getCode();
        } catch (MessagingException e) {
            log.error("发送html邮件时发生异常！", e);
        }
        return CodeMsgDTO.RESULT_FAILED.getCode();
    }


    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    @Override
    public Integer sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            File file = new File(filePath);
            //获取文件名
            String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/") + 1);
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            log.info("带附件的邮件已经发送。");
            return CodeMsgDTO.RESULT_SUCCESS.getCode();
        } catch (MessagingException e) {
            log.error("发送带附件的邮件时发生异常！", e);
        }
        return CodeMsgDTO.RESULT_FAILED.getCode();
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    @Override
    public Integer sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
            return CodeMsgDTO.RESULT_SUCCESS.getCode();
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
        return CodeMsgDTO.RESULT_FAILED.getCode();
    }

}