package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.enums.Status;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, String> {


    List<User> findAllByStatus(Status status);


    User findByUsername(String userName);

    User findUserById(Long id);
}
