package tn.pfeconnect.pfeconnect.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
class ChatNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Setter
    private String content;

    private int count=0;
    LocalDateTime timestamp = LocalDateTime.now();


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
