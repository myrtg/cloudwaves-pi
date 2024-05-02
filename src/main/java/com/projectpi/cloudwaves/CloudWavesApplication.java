package com.projectpi.cloudwaves;

import com.projectpi.cloudwaves.Services.EmailServiceImp;
import com.projectpi.cloudwaves.Services.StorageProperties;
import com.projectpi.cloudwaves.entites.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CloudWavesApplication {
    @Autowired
    private EmailServiceImp service;
    Utilisateur user = new Utilisateur();
    String userEmail = user.getEmail();
    public static void main(String[] args) {
        SpringApplication.run(CloudWavesApplication.class, args);
    }

}
