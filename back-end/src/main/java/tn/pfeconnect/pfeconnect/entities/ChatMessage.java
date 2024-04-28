package tn.pfeconnect.pfeconnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatMessage {
    @Id
    private String id;
    private Long chatId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id_user")
    private User sender;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipient_id_user")
    private User recipient;

    private String content;
    private Date timestamp;

}
