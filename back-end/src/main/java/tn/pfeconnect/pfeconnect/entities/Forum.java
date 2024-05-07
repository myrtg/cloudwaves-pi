package tn.pfeconnect.pfeconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Forums")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idForum", nullable = false)
    private Integer idForum;
    private String message;
    private Date datePublication;
    private String titre;
    private String email;

    @ManyToOne

    private User user;

    private String nomFichier;
    private byte[] fichier;

    private int likes;
    private int dislikes;

    @ManyToMany
    @JoinTable(
            name = "forum_likes",
            joinColumns = @JoinColumn(name = "forum_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likedByUsers;

    @ManyToMany
    @JoinTable(
            name = "forum_dislikes",
            joinColumns = @JoinColumn(name = "forum_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> dislikedByUsers;




}


