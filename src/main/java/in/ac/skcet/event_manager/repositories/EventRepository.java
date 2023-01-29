package in.ac.skcet.event_manager.repositories;

import in.ac.skcet.event_manager.models.Event;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByEndDateBeforeAndClassCodesLike(Date date, String s, PageRequest of);

}
