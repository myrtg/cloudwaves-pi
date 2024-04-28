package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.ChatMessage;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {


    List<ChatMessage> findByChatIdOrderByTimestampAsc(Long chatId);
}
