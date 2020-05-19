package com.cxd.myspringboot.service;


import javax.mail.MessagingException;

/**
 * 发送邮件
 */
public interface MailService {
    //发送文本邮件
    Integer sendSimpleMail(String to, String subject, String content);

    //发送HTML邮件
    Integer sendHtmlMail(String to, String subject, String content);

    //发送带附件的邮件
    Integer sendAttachmentsMail(String to, String subject, String content, String filePath);

    //发送带静态资源的邮件
    Integer sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}

