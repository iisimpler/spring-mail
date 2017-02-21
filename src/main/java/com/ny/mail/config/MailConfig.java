package com.ny.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan("com.ny.mail")
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Bean
    public JavaMailSenderImpl mailSender(Environment env) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mailServer.host"));
        mailSender.setPort(Integer.valueOf(env.getProperty("mailServer.port")));
        mailSender.setUsername(env.getProperty("mailServer.username"));
        mailSender.setPassword(env.getProperty("mailServer.password"));

        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }
}
