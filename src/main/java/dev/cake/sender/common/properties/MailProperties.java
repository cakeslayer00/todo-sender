package dev.cake.sender.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mail")
public record MailProperties(String host,
                             int port,
                             String username,
                             String password,
                             String from,
                             boolean smtpAuthEnabled,
                             boolean tlsEnabled) {
}
