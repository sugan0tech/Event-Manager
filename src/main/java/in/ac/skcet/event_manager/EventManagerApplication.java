package in.ac.skcet.event_manager;

import in.ac.skcet.event_manager.on_duty.OnDutyFormRepository;
import in.ac.skcet.event_manager.student.StudentMongoRepository;
import in.ac.skcet.event_manager.time_table.TimeTableRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class})
@EnableScheduling
@EnableMongoRepositories(basePackageClasses = {StudentMongoRepository.class, OnDutyFormRepository.class, TimeTableRepository.class})
public class EventManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}
}
