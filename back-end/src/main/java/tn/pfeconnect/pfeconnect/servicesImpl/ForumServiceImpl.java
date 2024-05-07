package tn.pfeconnect.pfeconnect.servicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.entities.Forum;
import tn.pfeconnect.pfeconnect.repositories.ForumRepository;
import tn.pfeconnect.pfeconnect.services.ForumService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ForumServiceImpl implements ForumService {


    ForumRepository forumRepository;
    private final List<String> badWords = Arrays.asList("tunis");

    @Override
    public Forum addForum(Forum forum) {
        return forumRepository.save(forum);
    }

    @Override
    public Forum updateForum(Forum forum) {
        return forumRepository.save(forum);
    }

    @Override
    public void removeForum(Integer id) {

        forumRepository.deleteById(id);

    }

    @Override
    public List<Forum> getAll() {
        return forumRepository.findAll();
    }

    @Override
    public Forum GetForm(int idForum) {
        return forumRepository.findById(idForum).get();

    }

    @Override
    public List<Forum> rechercherForumsParTitre(String titre) {
        return forumRepository.findByTitreContainingIgnoreCase(titre);
    }

    @Override
    public void likeForum(Integer forumId) {
        Forum forum = forumRepository.findById(forumId).orElse(null);
        if (forum != null) {
            forum.setLikes(forum.getLikes() + 1);
            forumRepository.save(forum);
        }

    }
    public void dislikeForum(Integer idForum) {
        Forum forum = forumRepository.findById(idForum).orElse(null);
        if (forum != null) {
            forum.setDislikes(forum.getDislikes() + 1);
            forumRepository.save(forum);
        }
    }
    //statistiques telles que le nombre total de forums, le nombre de likes et de dislikes
    @Override
    public Map<String, Long> getForumStatistics() {
        List<Forum> forums = forumRepository.findAll();

        long totalForums = forums.size();
        long totalLikes = forums.stream().mapToLong(Forum::getLikes).sum();
        long totalDislikes = forums.stream().mapToLong(Forum::getDislikes).sum();

        Map<String, Long> statistics = Map.of(
                "totalForums", totalForums,
                "totalLikes", totalLikes,
                "totalDislikes", totalDislikes
        );

        return statistics;
    }

    @Override
    public boolean forumContainsBadWord(Forum forum) {
        String message = forum.getMessage().toLowerCase(); // Convert to lowercase for case-insensitive comparison
        for (String badWord : badWords) {
            if (message.contains(badWord)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String translateForumMessage(Forum forum) {
        String langPair = "en|ar"; // Source and target language
        String encodedText = URLEncoder.encode(forum.getMessage());

        try {
            URL url = new URL("https://translate.googleapis.com/translate_a/single?client=gtx&sl=" + langPair.split("\\|")[0] + "&tl=" + langPair.split("\\|")[1] + "&dt=t&q=" + encodedText);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();

            // Log the response to check if it's correct
            System.out.println("Response from Google Translate API: " + response.toString());

            // Parse the response properly
            String translatedText = parseGoogleTranslateResponse(response.toString());

            if (translatedText != null) {
                return translatedText;
            } else {
                return "Translation failed.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String parseGoogleTranslateResponse(String response) {
        String[] parts = response.split("\",\"");
        String translation = parts[0].substring(4);
        return translation;
    }

    @Override
    public long getNumberOfForums() {
        return forumRepository.count();
    }

    @Override
    public Optional<Forum> getForumById(int id) {
        return forumRepository.findById(id);

    }


    // Méthode pour vérifier si la traduction contient des mots inappropriés
    private boolean containsBadWords(String text) {
        String[] badWords = {"badWord1", "badWord2", "badWord3"}; // Ajoutez vos mots inappropriés ici

        text = text.toLowerCase(); // Convertir en minuscules pour une comparaison insensible à la casse

        for (String badWord : badWords) {
            if (text.contains(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }


}