package tn.pfeconnect.pfeconnect.user;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);


}
