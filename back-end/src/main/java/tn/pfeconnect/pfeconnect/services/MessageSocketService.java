package tn.pfeconnect.pfeconnect.services;

import org.springframework.web.multipart.MultipartFile;
import tn.pfeconnect.pfeconnect.dtos.MessageRequest;

/**
 * An interface for handling real-time messaging functionality using web sockets.
 */
public interface MessageSocketService {

    /**
     * Send user conversations to a specific user by their user ID through a web socket.
     *
     * @param userId The ID of the user for whom to send conversations.
     */
    void sendUserConversationByUserId(int userId);

    /**
     * Send messages of a specific conversation to the connected users through a web socket.
     *
     * @param conversationId The ID of the conversation for which to send messages.
     */
    void sendMessagesByConversationId(int conversationId);

    /**
     * Save a new message using a web socket.
     *
     * @param msg The MessageRequest object containing the message details to be saved.
     */
    public void saveMessage(MessageRequest msg);
    /**
     * Delete a conversation by its unique conversation ID using a web socket.
     *
     * @param conversationId The ID of the conversation to be deleted.
     */
    void deleteConversationByConversationId(int conversationId);

    /**
     * Delete a message by its unique message ID within a conversation using a web socket.
     *
     * @param conversationId The ID of the conversation to notify its listener.
     * @param messageId      The ID of the message to be deleted.
     */
    void deleteMessageByMessageId(int conversationId, int messageId);
    /**
     * Mark messages as read in a given room for a specific receiver using a web socket.
     *
     * @param roomId         The ID of the room (conversation) containing the messages to be marked as read.
     * @param receiverUserId The ID of the receiver for whom the messages will be marked as read.
     */
    public void markMessagesAsReadInRoomForReceiver(int roomId, int receiverUserId);
}
