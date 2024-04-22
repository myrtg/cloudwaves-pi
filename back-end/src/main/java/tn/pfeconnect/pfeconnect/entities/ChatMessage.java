package tn.pfeconnect.pfeconnect.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tn.pfeconnect.pfeconnect.user.User;


import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="chat_message")
public class ChatMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idChat", nullable = false)
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    @JsonIgnore
    @ManyToOne
    private User sender;
    @JsonIgnore
    @ManyToOne
    private User recipient; // Champ pour spécifier le destinataire (admin)
    @JsonIgnore
    @ManyToOne
    private ChatRoom chatRoom;
}