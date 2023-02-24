package in.ac.skcet.event_manager.firebase_notification;

import in.ac.skcet.event_manager.firebase_notification.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String> {
}
