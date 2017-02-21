package com.ny.mail.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSenderImpl mailSender;

    public void sendSimpleEmail(String to,String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iisimpler@qq.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);

    }

    public void sendEmailWithAttachment(String to,String subject, String msg,ClassPathResource img) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("iisimpler@qq.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(msg);
        helper.addAttachment("coupon.jpg",img);
        mailSender.send(message);
    }

    public void sendEmailWithHtml(String to,String subject, String msg,ClassPathResource img) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("iisimpler@qq.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(msg,true);
        helper.addInline("logo",img);
        mailSender.send(message);
    }
}
