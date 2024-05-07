package tn.pfeconnect.pfeconnect.servicesImpl;

import org.springframework.web.multipart.MultipartFile;
import tn.pfeconnect.pfeconnect.dtos.*;
import tn.pfeconnect.pfeconnect.entities.Conversation;
import tn.pfeconnect.pfeconnect.entities.Message;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.repositories.ConversationRepository;
import tn.pfeconnect.pfeconnect.repositories.MessageRepository;
import tn.pfeconnect.pfeconnect.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.pfeconnect.pfeconnect.services.MessageSocketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the MessageSocketService interface that handles real-time messaging functionality using web sockets.
 */
@Service
@RequiredArgsConstructor
public class MessageSocketServiceImpl implements MessageSocketService {
    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    /**
     * Send user conversations to a specific user by their user ID through a web socket.
     *
     * @param userId The ID of the user for whom to send conversations.
     */
    @Override
    public void sendUserConversationByUserId(int userId) {
        List<ConversationResponse> conversation = conversationRepository.findConversationsByUserId(userId);
        messagingTemplate.convertAndSend(
                "/topic/user/".concat(String.valueOf(userId)),
                WebSocketResponse.builder()
                        .type("ALL")
                        .data(conversation)
                        .build()
        );
    }


    /**
     * Send messages of a specific conversation to the connected users through a web socket.
     *
     * @param conversationId The ID of the conversation for which to send messages.
     */
    @Override
    public void sendMessagesByConversationId(int conversationId) {
        Conversation conversation = new Conversation();
        conversation.setConversationId(conversationId);
        List<Message> messageList = messageRepository.findAllByConversation(conversation);
        List<MessageResponse> messageResponseList = messageList.stream()
                .map((message -> MessageResponse.builder()
                        .messageId(message.getMessageId())
                        .message(message.getMessage())
                        .timestamp(Date.from(message.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
                        .senderId(message.getSender().getId())
                        .receiverId(message.getReceiver().getId())
                        .build())
                ).toList();
        messagingTemplate.convertAndSend("/topic/conv/".concat(String.valueOf(conversationId)), WebSocketResponse.builder()
                .type("ALL")
                .data(messageResponseList)
                .build()
        );
    }


    /**
     * Save a new message using a web socket.
     *
     * @param msg The MessageRequest object containing the message details to be saved.
     */
    @Override
    public void saveMessage(MessageRequest msg) {
        System.out.println("MessageRequest   "+msg);
        User sender = userRepository.findById(msg.getSenderId()).get();
        User receiver = userRepository.findById(msg.getReceiverId()).get();
        Conversation conversation = conversationRepository.findConversationByUsers(sender, receiver).get();
        Message newMessage = new Message();

        newMessage.setMessage(msg.getMessage());
        newMessage.setTimestamp(msg.getTimestamp());
        newMessage.setConversation(conversation);
        newMessage.setSender(sender);
        newMessage.setReceiver(receiver);

        // Handle file upload (if applicable)
       /* if (file != null && !file.isEmpty()) {
            try {
                // Save the file to a designated folder on the server
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get("src/main/java/tn/pfeconnect/pfeconnect/storage/" + fileName);

                // Copy the file to the designated folder
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                newMessage.setFileName(fileName);
                newMessage.setFilePath(filePath.toString());

            } catch (IOException e) {
                // Handle exceptions related to file storage
                e.printStackTrace();
                // Optionally, you can throw a custom exception or log the error
            }
        }*/
        Message savedMessage = messageRepository.save(newMessage);
        // notify listener
        MessageResponse res = MessageResponse.builder()
                .messageId(savedMessage.getMessageId())
                .message(savedMessage.getMessage())
                .timestamp(Date.from(savedMessage.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()))
                .senderId(savedMessage.getSender().getId())
                .receiverId(savedMessage.getReceiver().getId())
                .build();
        messagingTemplate.convertAndSend("/topic/conv/".concat(msg.getConversationId().toString()),
                WebSocketResponse.builder()
                        .type("ADDED")
                        .data(res)
                        .build()
        );
        sendUserConversationByUserId(msg.getSenderId());
        sendUserConversationByUserId(msg.getReceiverId());
    }

    // Methods to handle file and image saving
    private String saveFile(MultipartFile file) {
        // Implement logic to save file to storage and return the file path
        return "path/to/saved/file";
    }

    private String saveImage(MultipartFile image) {
        // Implement logic to save image to storage and return the image path
        return "path/to/saved/image";
    }

    /**
     * Delete a conversation by its unique conversation ID using a web socket.
     *
     * @param conversationId The ID of the conversation to be deleted.
     */
    @Transactional
    @Override
    public void deleteConversationByConversationId(int conversationId) {
        Conversation c = new Conversation();
        c.setConversationId(conversationId);
        messageRepository.deleteAllByConversation(c);
        conversationRepository.deleteById(conversationId);
    }

    /**
     * Delete a message by its unique message ID within a conversation using a web socket.
     *
     * @param conversationId The ID of the conversation to notify its listener.
     * @param messageId      The ID of the message to be deleted.
     */
    @Override
    public void deleteMessageByMessageId(int conversationId, int messageId) {
        messageRepository.deleteById(messageId);
        // notify listener
        sendMessagesByConversationId(conversationId);
    }
    /**
     * Mark messages as read in a given room for a specific receiver using a web socket.
     *
     * @param roomId         The ID of the room (conversation) containing the messages to be marked as read.
     * @param receiverUserId The ID of the receiver for whom the messages will be marked as read.
     */
    @Override
    public void markMessagesAsReadInRoomForReceiver(int roomId, int receiverUserId) {
        // Find all messages in the given room for the specific receiver
        List<Message> messages = messageRepository.findAllByConversationIdAndReceiverId(roomId, receiverUserId);

        // Iterate over each message and mark it as read
        for (Message message : messages) {
            message.setRead(true);
        }

        // Save the updated messages
        messageRepository.saveAll(messages);
    }

}
