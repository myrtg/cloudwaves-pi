package com.cloudwaves.pfeconnect.token;

import com.cloudwaves.pfeconnect.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;
    @ManyToOne
    @JoinColumn(name= "idUser ", nullable = false)
    private User user;
}
