package tn.pfeconnect.pfeconnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tn.pfeconnect.pfeconnect.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoom", nullable = false)
    private Long chatId;
    //private String senderId;
    //private String recipientId;

    // Declare the list of users
    @OneToMany
    private List<User> users;
}
