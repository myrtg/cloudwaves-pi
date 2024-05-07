package tn.pfeconnect.pfeconnect.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.User;
import tn.pfeconnect.pfeconnect.enums.Status;

import java.util.List;
import java.util.Optional;
@Repository

public interface UserRepository  extends JpaRepository<User, String> {


    @Query("SELECT u FROM User u WHERE u.id <> ?1")
    List<User> findAllUsersExceptThisUserId(int userId);
}
