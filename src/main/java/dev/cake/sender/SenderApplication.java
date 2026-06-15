package dev.cake.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SenderApplication {

    static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

}
