package tn.pfeconnect.pfeconnect.controllers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.pfeconnect.pfeconnect.entities.ChatRoom;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.repositories.UserRepository;
import tn.pfeconnect.pfeconnect.services.IChatRoomService;
import tn.pfeconnect.pfeconnect.services.IUserService;

import java.util.List;

@RestController///choisir le type de body en form json
@RequestMapping("chatrooms")// le path du controller → tjr en minuscule
@AllArgsConstructor
public class ChatRoomController {

}

