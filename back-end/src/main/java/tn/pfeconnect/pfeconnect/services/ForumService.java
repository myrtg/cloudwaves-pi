package tn.pfeconnect.pfeconnect.services;

import tn.pfeconnect.pfeconnect.entities.Forum;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ForumService {
    Forum addForum(Forum forum);
    Forum updateForum(Forum forum);
    void removeForum(Integer id);
    List<Forum> getAll();
    Forum GetForm(int idForum);

    List<Forum> rechercherForumsParTitre(String titre) ;
    void likeForum(Integer forumId);
    void dislikeForum(Integer idForum);
    Map<String, Long> getForumStatistics();
    boolean forumContainsBadWord(Forum forum);
    String translateForumMessage(Forum forum);
    String parseGoogleTranslateResponse(String response);
    long getNumberOfForums();
    Optional<Forum> getForumById(int id);
}


