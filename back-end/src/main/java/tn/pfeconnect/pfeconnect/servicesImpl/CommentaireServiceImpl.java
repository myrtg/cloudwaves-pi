package tn.pfeconnect.pfeconnect.servicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.entities.Commentaire;
import tn.pfeconnect.pfeconnect.repositories.CommentaireRepository;
import tn.pfeconnect.pfeconnect.services.CommentaireService;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentaireServiceImpl implements CommentaireService {


    CommentaireRepository commentaireRepository;
    @Override
    public Commentaire addCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire updateCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public void removeCommentaire(Integer id) {
        commentaireRepository.deleteById(id);
    }

    @Override
    public List<Commentaire> getAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire GetCommentaire(int idCommentaire) {
        return commentaireRepository.findById(idCommentaire).get();
    }

    @Override
    public void likeCommentaire(Integer commentaireId) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId).orElse(null);
        if (commentaire != null) {
            commentaire.setLikes(commentaire.getLikes() + 1);
            commentaireRepository.save(commentaire);
        }
    }

    @Override
    public void dislikeCommentaire(Integer idCommentaire) {
        Commentaire commentaire= commentaireRepository.findById(idCommentaire).orElse(null);
        if (commentaire != null) {
            commentaire.setDislikes(commentaire.getDislikes() + 1);
            commentaireRepository.save(commentaire);
        }
    }
}