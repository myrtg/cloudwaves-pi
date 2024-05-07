package tn.pfeconnect.pfeconnect.user;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.user.Token;


import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
}
