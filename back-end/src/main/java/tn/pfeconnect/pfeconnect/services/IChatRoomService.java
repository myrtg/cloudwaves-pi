package tn.pfeconnect.pfeconnect.services;

import tn.pfeconnect.pfeconnect.entities.User;

import java.util.List;
import java.util.Optional;

public interface IChatRoomService {


    Optional<Long> getChatRoomId(User senderId, User recipientId, boolean createNewRoomIfNotExists);

    Long createChatId(List<User> users);
}
