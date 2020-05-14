package com.cxd.myspringboot.service;


import javax.mail.MessagingException;

/**
 * 发送邮件
 */
public interface MailService {

    //发送文本邮件
    String sendSimpleMail(String to, String subject, String content);

    //发送html邮件
    String sendHtmlMail(String to, String subject, String content) throws MessagingException;

    //发送带附件的邮件
    String sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException;

    //发送有图片的邮件
    String sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException;

}
