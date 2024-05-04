package tn.pfeconnect.pfeconnect.repositories;

import tn.pfeconnect.pfeconnect.entities.Conversation;
import tn.pfeconnect.pfeconnect.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByConversation(Conversation conversation);

    void deleteAllByConversation(Conversation conversation);
}
