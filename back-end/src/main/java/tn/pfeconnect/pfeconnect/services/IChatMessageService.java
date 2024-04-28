package tn.pfeconnect.pfeconnect.services;

import tn.pfeconnect.pfeconnect.dtos.ChatMessageDTO;
import tn.pfeconnect.pfeconnect.entities.ChatMessage;
import tn.pfeconnect.pfeconnect.entities.User;

import java.util.List;

public interface IChatMessageService {

    ChatMessage save(ChatMessage chatMessage);

    List<ChatMessageDTO> findChatMessages(User senderId, User recipientId);
}
