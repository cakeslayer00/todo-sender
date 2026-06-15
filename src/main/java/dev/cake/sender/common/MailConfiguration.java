package dev.cake.sender.common;

import dev.cake.sender.common.properties.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender(MailProperties properties) {
        var javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(properties.host());
        javaMailSender.setPort(properties.port());

        if (properties.smtpAuthEnabled()) {
            javaMailSender.setUsername(properties.username());
            javaMailSender.setPassword(properties.password());
        }

        Properties javaMailProperties = javaMailSender.getJavaMailProperties();
        javaMailProperties.setProperty("mail.smtp.auth", String.valueOf(properties.smtpAuthEnabled()));
        javaMailProperties.setProperty("mail.smtp.starttls.enable", String.valueOf(properties.tlsEnabled()));
        return javaMailSender;
    }

}
