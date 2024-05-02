package tn.pfeconnect.pfeconnect.services;


import tn.pfeconnect.pfeconnect.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {


    void saveUser(User user);

    void disconnect(User user);

    List<User> findConnectedUsers();

    User findUserById(long l);

    User findByNickname(String userName);
}
