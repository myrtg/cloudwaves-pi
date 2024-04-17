package com.cloudwaves.pfeconnect.repositories;

import com.cloudwaves.pfeconnect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
