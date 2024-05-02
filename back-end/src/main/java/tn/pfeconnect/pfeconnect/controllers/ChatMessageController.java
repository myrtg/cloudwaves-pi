package tn.pfeconnect.pfeconnect.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import tn.pfeconnect.pfeconnect.dtos.ChatMessageDTO;
import tn.pfeconnect.pfeconnect.entities.ChatMessage;
import tn.pfeconnect.pfeconnect.entities.ChatNotification;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.services.IChatMessageService;
import tn.pfeconnect.pfeconnect.services.IUserService;

import java.util.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class ChatMessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final IChatMessageService chatMessageService;
    private final IUserService userService;

    @MessageMapping("/chat")
    public void processMessage(@Payload Map<String,Object> payload) {
        // Extract senderID, recipientID, and content from the payload
        String senderId = (String) payload.get("senderId");
        String recipientId = (String) payload.get("recipientId");
        String content = (String) payload.get("message");

        System.out.println(" (String) payload.get(\"message\") "+  (String) payload.get("message") + senderId +"  "+ recipientId+ "payload"+ payload);

        // Fetch sender and recipient users from their IDs
        User sender = userService.findByNickname(senderId);
        User recipient = userService.findByNickname(recipientId);

        // Create a new ChatMessage object
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(sender);
        chatMessage.setRecipient(recipient);
        chatMessage.setContent(content);
        chatMessage.setTimestamp(new Date()); // Set the timestamp as needed
        System.out.println("chatMessage "+ chatMessage);

        // Save the chatMessage
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        System.out.println("savedMsg "+ savedMsg);

        // Send a notification to the recipient
        messagingTemplate.convertAndSendToUser(
                recipientId, "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        sender.getUsername(),
                        recipient.getUsername(),
                        savedMsg.getContent()
                )
        );
    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessageDTO>> findChatMessages(@PathVariable String senderId,
                                                                 @PathVariable String recipientId) {
        System.out.println("senderId "+ senderId+"  "+ recipientId);
        // Fetch sender and recipient users from the repository
        User sender = userService.findByNickname(senderId);
        User recipient = userService.findByNickname(recipientId);

        // Check if sender and recipient exist
        if (sender == null || recipient == null) {
            // Return appropriate response if sender or recipient is not found
            return ResponseEntity.notFound().build();
        }

        // Fetch pfeconnect messages using sender and recipient
        List<ChatMessageDTO> chatMessages = chatMessageService.findChatMessages(sender, recipient);

        // Return the fetched pfeconnect messages
        return ResponseEntity.ok(chatMessages);
    }
}




