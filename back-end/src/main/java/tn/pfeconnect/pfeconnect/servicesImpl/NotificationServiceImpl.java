package tn.pfeconnect.pfeconnect.servicesImpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    public NotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendNotification(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("nadejlengliz07@gmail.com");
        mailMessage.setSubject("Notification");
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
