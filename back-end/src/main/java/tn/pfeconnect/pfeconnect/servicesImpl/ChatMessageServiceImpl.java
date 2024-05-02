package tn.pfeconnect.pfeconnect.servicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.dtos.ChatMessageDTO;
import tn.pfeconnect.pfeconnect.entities.ChatMessage;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.repositories.ChatMessageRepository;
import tn.pfeconnect.pfeconnect.services.IChatMessageService;
import tn.pfeconnect.pfeconnect.services.IChatRoomService;
import tn.pfeconnect.pfeconnect.services.IUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatMessageServiceImpl implements IChatMessageService {
    private final ChatMessageRepository repository;
    private final IChatRoomService chatRoomService;
    private final IUserService userService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        // Fetch sender and recipient users from the repository
        User sender = chatMessage.getSender();
        User recipient = chatMessage.getRecipient();

        // Check if sender and recipient exist
        if (sender == null || recipient == null) {
            throw new RuntimeException("Sender or recipient not found");
        }

        // Set sender and recipient in the pfeconnect message
        chatMessage.setSender(sender);
        chatMessage.setRecipient(recipient);
        chatMessage.setContent(chatMessage.getContent());
        System.out.println("content +"+ chatMessage.getContent());
        // Set other properties of chatMessage
        chatMessage.setTimestamp(new Date());

        Long chatId = chatRoomService
                .getChatRoomId(sender, recipient, true)
                .orElseThrow(() -> new RuntimeException("pfeconnect room ID not found")); // or your custom exception
        chatMessage.setChatId(chatId);

        // Generate a unique identifier with UUID
        chatMessage.setId(UUID.randomUUID().toString());

        // Save the pfeconnect message
        repository.save(chatMessage);

        return chatMessage;
    }



   /* public List<ChatMessage> findChatMessages(User senderId, User recipientId) {
        System.out.println("users + " + senderId +" "+recipientId);
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        System.out.println("chatId + " + chatId);
        System.out.println("messages + " + chatId.map(repository::findByChatIdOrderByTimestampAsc).orElse(new ArrayList<>()));

        return chatId.map(repository::findByChatIdOrderByTimestampAsc).orElse(new ArrayList<>());
    }*/
    public List<ChatMessageDTO> findChatMessages(User sender, User recipient) {
        var chatId = chatRoomService.getChatRoomId(sender, recipient, false);
        return chatId.map(id -> {
            List<ChatMessage> messages = repository.findByChatIdOrderByTimestampAsc(id);
            return messages.stream()
                    .map(message -> new ChatMessageDTO(
                            message.getId(),
                            message.getChatId(),
                            message.getSender().getNickname(),
                            message.getRecipient().getNickname(),
                            message.getContent(),
                            message.getTimestamp(),
                            message.getSender()
                    ))
                    .collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
