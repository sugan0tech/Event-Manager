package in.ac.skcet.event_manager.time_table;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeTableRepository extends MongoRepository<TimeTable, String> {
}
