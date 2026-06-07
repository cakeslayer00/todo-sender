package dev.cake.sender.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mail")
public record MailProperties(String host,
                             Integer port,
                             String sender,
                             String password,
                             String protocol) {
}
