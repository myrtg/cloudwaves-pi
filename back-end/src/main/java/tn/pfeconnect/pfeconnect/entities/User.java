package tn.pfeconnect.pfeconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.pfeconnect.pfeconnect.enums.Roles;
import tn.pfeconnect.pfeconnect.enums.Status;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TUser")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private Long id;
    private String username;
    @Enumerated(EnumType.STRING)
    private Roles role;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> sentMessages;

    @OneToMany(mappedBy = "recipient")
    private List<ChatMessage> receivedMessages;
}