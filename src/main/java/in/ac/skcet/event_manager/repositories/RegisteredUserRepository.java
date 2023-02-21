package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {
    Optional<RegisteredUser> findByEmail(String email);
}
