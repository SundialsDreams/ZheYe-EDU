package com.example.web.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    public MailService() {
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername(from);
        mailSender.setPassword("hkhmaojvnmwwbcee");
    }

    private String from="643752571@qq.com";

   public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        System.out.println(from);
        System.out.println(to);
        System.out.println(subject);
        System.out.println(content);
       System.out.println(message);
        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }
}
