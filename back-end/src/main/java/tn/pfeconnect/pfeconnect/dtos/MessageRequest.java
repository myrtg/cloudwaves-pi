package tn.pfeconnect.pfeconnect.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    Integer conversationId;
    Integer senderId;
    Integer receiverId;
    String message;
    LocalDateTime timestamp;




    // Add getter and setter for image

}
