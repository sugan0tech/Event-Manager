package in.ac.skcet.event_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class})
public class EventManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}
	// test
}
