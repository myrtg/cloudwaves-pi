package tn.pfeconnect.pfeconnect.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.enums.Status;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);


    List<User> findAllByStatus(Status status);


    User findByUsername(String userName);

    User findUserById(Long id);
}
