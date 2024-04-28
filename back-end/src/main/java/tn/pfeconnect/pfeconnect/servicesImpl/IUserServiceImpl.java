package tn.pfeconnect.pfeconnect.servicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.enums.Status;
import tn.pfeconnect.pfeconnect.repositories.UserRepository;
import tn.pfeconnect.pfeconnect.services.IUserService;


import java.util.List;

@Service
@AllArgsConstructor
public class IUserServiceImpl implements IUserService {
    private final UserRepository repository;
    @Override
    public void saveUser(User user) {
        // Check if the user already exists by userName or fullName
        User existingUser = repository.findByUsername(user.getUsername());

        if (existingUser != null) {
            // Update the existing user
            existingUser.setStatus(Status.ONLINE);
            repository.save(existingUser);
        } else {
            // Save the new user
            user.setStatus(Status.ONLINE);
            repository.save(user);
        }
    }

    @Override
    public void disconnect(User user) {
        var storedUser = repository.findByUsername(user.getUsername());
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }
    @Override
    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }

    @Override
    public User findUserById(long l) {
        return repository.findUserById(l);
    }

    @Override
    public User findByUserName(String n){
        return repository.findByUsername(n);
    }

}
