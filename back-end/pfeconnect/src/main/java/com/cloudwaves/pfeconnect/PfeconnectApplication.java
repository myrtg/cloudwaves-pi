package com.cloudwaves.pfeconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PfeconnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfeconnectApplication.class, args);
    }

}
