package tn.pfeconnect.pfeconnect.role;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.pfeconnect.pfeconnect.role.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
