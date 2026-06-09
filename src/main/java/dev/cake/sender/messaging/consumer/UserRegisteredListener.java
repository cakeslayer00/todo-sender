package dev.cake.sender.messaging.consumer;

import dev.cake.sender.messaging.event.EmailVerificationRequestedEvent;
import dev.cake.sender.properties.MailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRegisteredListener {

    private final JavaMailSender mailSender;

    private final MailProperties mailProperties;

    @KafkaListener(topics = "auth.email-verification-requested", groupId = "sender")
    public void onUserRegistered(EmailVerificationRequestedEvent event) {
        log.info("Received UserRegisteredEvent for {}", event.publicId());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(mailProperties.sender());
        msg.setTo(event.email());
        msg.setSubject("Confirmation letter");
        msg.setText("""
                Dear %s
                
                To confirm that this email belongs to you must continue by this link:
                http://localhost:8080/api/v1/auth/verify?token=%s
                
                Sincerely yours,
                v
                """.formatted(event.username(), event.token()));
        try {
            this.mailSender.send(msg);
            log.info("Confirmation email sent to user {}", event.publicId());
        } catch (MailException ex) {
            log.error("Error occured: {}", ex.getMessage());
        }
    }

}