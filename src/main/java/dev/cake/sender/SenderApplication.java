package dev.cake.sender;

import dev.cake.sender.properties.MailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
@EnableConfigurationProperties(MailProperties.class)
public class SenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}

	@Bean
	public JavaMailSender getJavaMailSender(MailProperties properties) {
		var javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(properties.host());
		javaMailSender.setPort(properties.port());
		javaMailSender.setUsername(properties.sender());
		javaMailSender.setPassword(properties.password());
		javaMailSender.setProtocol(properties.protocol());
	}
}
