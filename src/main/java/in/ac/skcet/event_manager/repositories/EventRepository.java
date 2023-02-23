package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
