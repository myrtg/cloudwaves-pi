package tn.pfeconnect.pfeconnect.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Commentaire")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCommentaire")
    private int idcommentaire;
    private String commentaire;
    private int likes;
    private int dislikes;
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Forum forum;

//    @ManyToOne
//    private User reponseCreatedBy;

//    @ManyToMany
//    @JoinTable(
//            name = "reponse_likes",
//            joinColumns = @JoinColumn(name = "reponse_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> likedByUser;
//
//    @ManyToMany
//    @JoinTable(
//            name = "reponse_dislikes",
//            joinColumns = @JoinColumn(name = "reponse_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> dislikedByUser;


}