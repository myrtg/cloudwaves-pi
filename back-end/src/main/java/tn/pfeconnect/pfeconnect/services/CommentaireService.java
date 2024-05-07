package tn.pfeconnect.pfeconnect.services;

import tn.pfeconnect.pfeconnect.entities.Commentaire;

import java.util.List;

public interface CommentaireService {
    Commentaire addCommentaire(Commentaire commentaire);
    Commentaire updateCommentaire(Commentaire commentaire);
    void removeCommentaire(Integer id);
    List<Commentaire> getAll();
    Commentaire GetCommentaire(int idcommentaire);
    void likeCommentaire(Integer commentaireId);
    void dislikeCommentaire(Integer idCommentaire);
}