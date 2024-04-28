package tn.pfeconnect.pfeconnect.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.pfeconnect.pfeconnect.entities.User;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
    private String id;
    private Long chatId;
    private String senderUserName;
    private String recipientUserName;
    private String content;
    private Date timestamp;
    private User sender;
}
