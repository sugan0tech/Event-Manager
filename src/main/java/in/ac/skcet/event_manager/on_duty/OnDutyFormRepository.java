package in.ac.skcet.event_manager.on_duty;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OnDutyFormRepository extends MongoRepository<OnDutyForm, String> {
}
