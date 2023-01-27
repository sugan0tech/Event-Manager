package in.ac.skcet.event_manager.services;

import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.repositories.EventRepository;
import in.ac.skcet.event_manager.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {
    StudentRepository studentRepository;
    EventRepository eventRepository;
    public List<Event> findById(String studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student == null)
            return new ArrayList<>();
        log.info(student.toString());
        return eventRepository.findAll().stream().filter(event -> !student.getEvents().contains(event)).collect(Collectors.toList());
    }
}
