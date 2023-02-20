package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {
    RegisteredUser findByEmail(String email);
}
