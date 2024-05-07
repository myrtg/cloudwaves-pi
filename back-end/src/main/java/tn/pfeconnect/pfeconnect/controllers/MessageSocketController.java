package tn.pfeconnect.pfeconnect.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import tn.pfeconnect.pfeconnect.dtos.MessageRequest;
import tn.pfeconnect.pfeconnect.services.MessageSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Controller class that handles real-time messaging using WebSocket communication.
 * Routes:
 * - /user: Send user conversations to a specific user by their user ID through a web socket.
 * - /conv: Send messages of a specific conversation to the connected users through a web socket.
 * - /sendMessage: Save a new message using a web socket.
 * - /deleteConversation: Delete a conversation by its unique conversation ID using a web socket.
 * - /deleteMessage: Delete a message by its unique message ID within a conversation using a web socket.
 */

@RequiredArgsConstructor
@Controller
public class MessageSocketController {
    private final MessageSocketService socketService;

    /**
     * Send user conversations to a specific user by their user ID through a web socket.
     *
     * @param userId The ID of the user for whom to send conversations.
     */
    @MessageMapping("/user")
    public void sendUserConversationByUserId(int userId) {
        socketService.sendUserConversationByUserId(userId);
    }

    /**
     * Send messages of a specific conversation to the connected users through a web socket.
     *
     * @param conversationId The ID of the conversation for which to send messages.
     */
    @MessageMapping("/conv")
    public void sendMessagesByConversationId(int conversationId) {
        socketService.sendMessagesByConversationId(conversationId);
    }

    /**
     * Save a new message using a web socket.
     *
     * @param message The MessageRequest object containing the message details to be saved.
     */
    @MessageMapping("/sendMessage")
    public void saveMessage(MessageRequest message) {
        socketService.saveMessage(message);
    }

    /**
     * Delete a conversation by its unique conversation ID using a web socket.
     *
     * @param payload A Map containing the conversationId, user1Id, and user2Id for the
     *                conversation to be deleted and notify listener.
     */
    @MessageMapping("/deleteConversation")
    public void deleteConversation(Map<String, Object> payload) {
        int conversationId = (int) payload.get("conversationId");
        int user1 = (int) payload.get("user1Id");
        int user2 = (int) payload.get("user2Id");
        socketService.deleteConversationByConversationId(conversationId);
        socketService.sendUserConversationByUserId(user1);
        socketService.sendUserConversationByUserId(user2);
    }
    /**
     * Mark messages as read in a given room for a specific receiver using a web socket.
     *
     * @param payload A Map containing the roomId and receiverUserId for marking messages as read.
     */
    @MessageMapping("/markAsRead")
    public void markMessagesAsReadInRoomForReceiver(Map<String, Integer> payload) {
        int roomId = payload.get("roomId");
        int receiverUserId = payload.get("receiverUserId");
        socketService.markMessagesAsReadInRoomForReceiver(roomId, receiverUserId);
    }


}
