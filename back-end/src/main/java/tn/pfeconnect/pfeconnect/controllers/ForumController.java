package tn.pfeconnect.pfeconnect.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import tn.pfeconnect.pfeconnect.entities.Forum;
import tn.pfeconnect.pfeconnect.repositories.ForumRepository;
import tn.pfeconnect.pfeconnect.repositories.UserRepository;
import tn.pfeconnect.pfeconnect.services.ForumService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("forums")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ForumController {
    ForumService forumService;


    UserRepository userRepository;


    @PostMapping("/add-forum")

    public Forum addForum(@RequestBody Forum forum) {
        return forumService.addForum(forum);
    }


//    @PostMapping("/add-forum")
//    public ResponseEntity<?> addForum(@RequestBody Forum forum) {
//        // Vérification de contenu
//        if (forumService.forumContainsBadWord(forum)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Forum contains prohibited word.");
//        }
//
//        // Initialisation de la date de publication
//        Date datePublication = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
//        forum.setDatePublication(datePublication);
//
//        // Initialisation des autres propriétés du forum si nécessaire
//        forum.setLikes(0);
//        forum.setDislikes(0);
//
//        // Ajout du forum
//        Forum newForum = forumService.addForum(forum);
//
//        // Renvoyer le forum nouvellement ajouté dans la réponse
//        return ResponseEntity.status(HttpStatus.CREATED).body(newForum);
//    }


    @GetMapping("/getAllForum")
    public List<Forum> getAll() {
        List<Forum> listForums = (List<Forum>) forumService.getAll();
        return listForums;
    }

    @PutMapping("/update-forum")
    public Forum UpdateForum(@RequestBody Forum forum) {
        return forumService.updateForum(forum);
    }

    @DeleteMapping("/delete-forum/{id-forum}")
    public void removeForum(@PathVariable("id-forum") int idForum) {
        forumService.removeForum(idForum);
    }


    @GetMapping("search")
    public List<Forum> rechercherForumsParTitre(@RequestParam String titre) {
        return forumService.rechercherForumsParTitre(titre);
    }

    //recherche par date
    @GetMapping("/searchForum")
    List<Forum> searchForum(
            @RequestParam(value = "datePublication", required = false) String datePublicationStr,
            @RequestParam(value = "titre", required = false) String titre) {
        List<Forum> allForums = forumService.getAll(); // Assuming you have a service class to retrieve all forums

        return allForums.stream()
                .filter(forum -> (datePublicationStr == null || dateMatches(forum.getDatePublication(), datePublicationStr)) &&
                        (titre == null || forum.getTitre().equalsIgnoreCase(titre)))
                .collect(Collectors.toList());
    }

    private boolean dateMatches(Date date, String dateStr) {
        if (date == null) {
            return false; // ou une autre logique selon votre besoin
        }

        // Convertir la Date en LocalDate
        LocalDate rdvLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate providedDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);

        return rdvLocalDate.equals(providedDate);
    }

    //upload


//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
//                                             @RequestParam("email") String email,
//                                             @RequestParam("message") String message,
//                                             @RequestParam("titre") String titre) {
//        try {
//            Forum forum = new Forum();
//            forum.setMessage(message);
//            forum.setDatePublication(new Date());
//            forum.setTitre(titre);
//            // Ajouter la logique pour récupérer l'utilisateur en fonction de l'email
//            forum.setEmail(email);
//
//            if (!file.isEmpty()) {
//                forum.setNomFichier(file.getOriginalFilename());
//                forum.setFichier(file.getBytes());
//            }
//
//            forumRepository.save(forum);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body("Forum message created successfully!");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
//        }
//    }

    //like et dislike
    @PostMapping("/forums/{forumId}/like")
    public ResponseEntity<String> likeForumMessage(@PathVariable Integer forumId) {
        if (forumId == null) {
            return ResponseEntity.badRequest().body("Forum ID is required.");
        }
        forumService.likeForum(forumId);
        return ResponseEntity.ok("Forum message liked successfully!");
    }

    @PostMapping("/forums/{forumId}/dislike")
    public ResponseEntity<String> dislikeForumMessage(@PathVariable Integer forumId) {
        if (forumId == null) {
            return ResponseEntity.badRequest().body("Forum ID is required.");
        }
        forumService.dislikeForum(forumId);
        return ResponseEntity.ok("Forum message disliked successfully!");
    }


    @GetMapping("/forum/statistics")
    public ResponseEntity<Map<String, Long>> getForumStatistics() {
        Map<String, Long> statistics = forumService.getForumStatistics();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/forum/{id}")
    public ResponseEntity<String> tweetForum(@PathVariable("id") int id) {
        // Utilisez Optional pour récupérer le Forum
        Forum forum = forumService.getForumById(id)
                .orElseThrow(() -> new RuntimeException("Forum not found with id: " + id));

        // Récupérer le message du forum
        String forumMessage = forum.getMessage();

        // Encoder le message du forum pour l'URL
        String encodedForumMessage = UriComponentsBuilder.fromUriString(forumMessage).build().encode().toString();

        // Construire l'URL d'intention Twitter avec le texte prérempli
        String twitterIntentUrl = "https://twitter.com/intent/tweet?text=" + encodedForumMessage;

        // Retourner l'URL en tant que réponse au client
        return ResponseEntity.status(HttpStatus.OK).body(twitterIntentUrl);
    }


    @GetMapping("/forums/{id}/translate")
    public ResponseEntity<String> translateForumMessage(@PathVariable Integer id) {
        Forum forum = forumService.GetForm(id);
        if (forum == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Forum not found with id: " + id);
        }

        String translatedMessage = forumService.translateForumMessage(forum);
        if (translatedMessage == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Translation failed.");
        }

        return ResponseEntity.ok(translatedMessage);
    }

//    @PostMapping("/forums/checkBadWords")
//    public ResponseEntity<String> checkBadWords(@RequestBody String text) {
//        boolean containsBadWords = containsBadWords(text);
//        if (containsBadWords) {
//            return ResponseEntity.status(HttpStatus.OK).body("Le texte contient des mots inappropriés.");
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body("Le texte ne contient pas de mots inappropriés.");
//        }
//    }
//
//    private boolean containsBadWords(String text) {
//        String[] badWords = {"chbikk", "wooo", "ohhh"};
//
//        text = text.toLowerCase(); // Convertir en minuscules pour une comparaison insensible à la casse
//
//        for (String badWord : badWords) {
//            if (text.contains(badWord.toLowerCase())) {
//                return true;
//            }
//        }
//        return false;


//}



    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        // Logique pour uploader le fichier
        return ResponseEntity.ok("Fichier uploadé avec succès");
    }

}



