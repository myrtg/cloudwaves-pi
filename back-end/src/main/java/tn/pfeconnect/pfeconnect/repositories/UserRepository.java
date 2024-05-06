package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.entities.User;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {


}
