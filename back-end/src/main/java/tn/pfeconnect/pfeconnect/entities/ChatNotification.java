package tn.pfeconnect.pfeconnect.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatNotification {
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
