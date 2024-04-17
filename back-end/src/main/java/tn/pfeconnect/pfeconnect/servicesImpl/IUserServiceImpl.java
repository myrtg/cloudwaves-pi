package tn.pfeconnect.pfeconnect.servicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.repositories.UserRepository;
import tn.pfeconnect.pfeconnect.services.IUserService;


import java.util.List;

@Service
@AllArgsConstructor////important pour l'injection de dependance
public class IUserServiceImpl implements IUserService {
    UserRepository userRepository;




}
