package tn.pfeconnect.pfeconnect.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.pfeconnect.pfeconnect.entities.Commentaire;
import tn.pfeconnect.pfeconnect.services.CommentaireService;

import java.util.List;

@RestController
@RequestMapping("commentaires")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CommentaireController {
    CommentaireService commentaireService;

    @PostMapping("/add-commentaire")

    public Commentaire addCommentaire(@RequestBody Commentaire commentaire) {
        return commentaireService.addCommentaire(commentaire);
    }
    @GetMapping("/getAllCommentaire")
    public List<Commentaire> getAll() {
        List<Commentaire> listCommentaires = (List<Commentaire>) commentaireService.getAll();
        return listCommentaires;
    }
    @PutMapping("/update-commentaire")
    public Commentaire UpdateCommentaire(@RequestBody Commentaire commentaire) {
        return commentaireService.updateCommentaire(commentaire);

    }


    @DeleteMapping("/delete-commentaire/{id-commentaire}")
    public void removeCommentaire(@PathVariable("id-commentaire") int idcommentaire) {
        commentaireService.removeCommentaire(idcommentaire);
    }
    @PostMapping("/commentaires/{commentaireId}/like")
    public ResponseEntity<String> likeCommentaire(@PathVariable Integer commentaireId) {
        if (commentaireId == null) {
            return ResponseEntity.badRequest().body("Commentaire ID is required.");
        }
        commentaireService.likeCommentaire(commentaireId);
        return ResponseEntity.ok("Commentaire message liked successfully!");
    }

    @PostMapping("/commentaires/{commentaireId}/dislike")
    public ResponseEntity<String> dislikeCommentaire(@PathVariable Integer commentaireId) {
        if (commentaireId == null) {
            return ResponseEntity.badRequest().body("Commentaire ID is required.");
        }
        commentaireService.dislikeCommentaire(commentaireId);
        return ResponseEntity.ok("Commentaire message disliked successfully!");
    }



}
