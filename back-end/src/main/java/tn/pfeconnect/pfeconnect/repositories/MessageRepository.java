package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.Query;
import tn.pfeconnect.pfeconnect.entities.Conversation;
import tn.pfeconnect.pfeconnect.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByConversation(Conversation conversation);

    void deleteAllByConversation(Conversation conversation);
    @Query("SELECT m FROM Message m WHERE m.conversation.conversationId = :conversationId AND m.receiver.id = :receiverId")
    List<Message> findAllByConversationIdAndReceiverId(int conversationId, int receiverId);
}
