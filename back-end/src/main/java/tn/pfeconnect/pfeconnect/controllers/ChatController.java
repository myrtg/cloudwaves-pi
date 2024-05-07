package tn.pfeconnect.pfeconnect.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import tn.pfeconnect.pfeconnect.services.MessageSocketService;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final MessageSocketService socketService;
    /**
     /**
     * Mark messages as read in a given room for a specific receiver using a web socket.
     *
     * @param roomId         The ID of the room (conversation) containing the messages to be marked as read.
     * @param receiverUserId The ID of the receiver for whom the messages will be marked as read.
     */
    @PutMapping("/markAsRead/{roomId}/{receiverUserId}")
    public void markMessagesAsReadInRoomForReceiver(
            @PathVariable int roomId,
            @PathVariable int receiverUserId
    ) {
        socketService.markMessagesAsReadInRoomForReceiver(roomId, receiverUserId);
    }
}
