package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByClassCodesLike(String s);
}
