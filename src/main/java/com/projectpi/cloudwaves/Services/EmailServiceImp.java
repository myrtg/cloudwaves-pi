package com.projectpi.cloudwaves.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Base64;

@Service
public class EmailServiceImp{
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String body, String htmlContent, byte[] qrCodeBytes) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);  // Use body for plain text

            // Create ByteArrayResource from QR code bytes
            ByteArrayResource resource = new ByteArrayResource(qrCodeBytes);
            helper.addInline("qrCodeImage", resource, "image/png");  // Add image with content ID and type

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
