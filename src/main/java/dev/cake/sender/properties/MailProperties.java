package dev.cake.sender.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mail")
public record MailProperties(String host,
                             int port,
                             String sender,
                             String password) {
}
