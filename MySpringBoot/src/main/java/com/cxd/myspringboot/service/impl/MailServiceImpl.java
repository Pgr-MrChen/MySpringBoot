package com.cxd.myspringboot.service.impl;

import com.cxd.myspringboot.service.MailService;
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
public class MailServiceImpl implements MailService {

    @Value(value = "${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    //发送文本邮件
    @Override
    public String sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
        return "1";
    }

    //发送HTML邮件
    @Override
    public String sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(content, true);
        mailSender.send(message);
        return "1";
    }

    @Override
    public String sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(content, true);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        String fileName = fileSystemResource.getFilename();
        helper.addAttachment(fileName, fileSystemResource);
        mailSender.send(message);
        return "1";
    }

    //发送有图片的邮件
    @Override
    public String sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(content, true);

        FileSystemResource rsc = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, rsc);
        mailSender.send(message);
        return "1";
    }


}
