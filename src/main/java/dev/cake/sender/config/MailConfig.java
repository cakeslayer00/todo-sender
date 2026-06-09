package dev.cake.sender.config;

import dev.cake.sender.properties.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender(MailProperties properties) {
        var javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(properties.host());
        javaMailSender.setPort(properties.port());
        javaMailSender.setUsername(properties.sender());
        javaMailSender.setPassword(properties.password());

        Properties javaMailProperties = javaMailSender.getJavaMailProperties();
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        return javaMailSender;
    }

}
