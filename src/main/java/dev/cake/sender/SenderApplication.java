package dev.cake.sender;

import dev.cake.sender.properties.MailProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties(MailProperties.class)
public class SenderApplication {

    static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

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

    @Bean
    public CommandLineRunner sendEmail(JavaMailSender javaMailSender) {
        return _ -> {
            var msg = new SimpleMailMessage();
            msg.setTo("_@gmail.com");
            msg.setSubject("Regarding email sending service");
            msg.setText(
                    "Dear " + "v"
                            + ", thank you for enormous contribution to this project. You are great!");
            try {
                javaMailSender.send(msg);
            } catch (MailException ex) {
                IO.println("Unable to send email: " + ex.getMessage());
            }
        };
    }

}
