package tn.pfeconnect.pfeconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.pfeconnect.pfeconnect.entities.ChatRoom;
import tn.pfeconnect.pfeconnect.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    //Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
    //@Query("SELECT cr FROM ChatRoom cr WHERE cr.users = :users")
    @Query("SELECT cr FROM ChatRoom cr JOIN cr.users u WHERE u IN :users GROUP BY cr HAVING COUNT(DISTINCT u) = :count")
    Optional<ChatRoom> findByUsers(@Param("users") List<User> users, @Param("count") Long count);


}
