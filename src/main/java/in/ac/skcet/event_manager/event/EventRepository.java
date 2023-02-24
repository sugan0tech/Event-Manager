package in.ac.skcet.event_manager.event;

import in.ac.skcet.event_manager.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
