package tn.pfeconnect.pfeconnect.servicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.entities.ChatRoom;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.repositories.ChatRoomRepository;
import tn.pfeconnect.pfeconnect.repositories.UserRepository;
import tn.pfeconnect.pfeconnect.services.IChatRoomService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public



class ChatRoomServiceImpl implements IChatRoomService {
    ChatRoomRepository chatRoomRepository;
    UserRepository userRepository;

    @Override
    public Optional<Long> getChatRoomId(User sender, User recipient, boolean createNewRoomIfNotExists) {

        List<User> users= List.of(sender,recipient);

        return chatRoomRepository
                .findByUsers(users, (long) users.size())
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        var chatId = createChatId(users);
                        return Optional.of(Long.valueOf(chatId));
                    }
                    return Optional.empty();
                });
    }

    @Override
    public Long createChatId(List<User> users) {
      //  var chatId = String.format("%s_%s", senderId, recipientId);
        ChatRoom senderRecipient = ChatRoom
                .builder()
                .users(users)
                .build();

        /*ChatRoom recipientSender = ChatRoom
                .builder()
                //  .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();*/
        System.out.println("senderRecipient " + senderRecipient);
        //System.out.println("recipientSender " + recipientSender);
        // Attribuer manuellement l'identifiant aux instances de ChatRoom
        // senderRecipient.setId(chatId);
        //recipientSender.setId(chatId);

       ChatRoom room1 = chatRoomRepository.save(senderRecipient);
        System.out.println("room1 " + room1);

        return room1.getChatId();
    }


}
