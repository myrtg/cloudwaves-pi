package tn.pfeconnect.pfeconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.pfeconnect.pfeconnect.enums.Roles;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


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
    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
    Set<ChatMessage> messages;

    @ManyToMany(mappedBy = "users")
    private List<ChatRoom> chatRooms;
}