package in.ac.skcet.event_manager.services;

import in.ac.skcet.event_manager.models.Event;
import in.ac.skcet.event_manager.models.Student;
import in.ac.skcet.event_manager.models.Teacher;
import in.ac.skcet.event_manager.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class TeacherService {

    TeacherRepository teacherRepository;
    EventService eventService;

    public Optional<Teacher> findById(String staffId){
        return teacherRepository.findById(staffId);
    }

    public List<Event> findEvents(String teacherId){
        String teacherClassCode = teacherRepository.findById(teacherId).orElse(new Teacher()).getClassCode();
        return eventService.findAll().stream().filter(event -> event.getClassCode().equals(teacherClassCode)).collect(Collectors.toList());
    }
    public Set<Teacher> findByClassCode(String classCode){
        return teacherRepository.findByClassCode(classCode);
    }

}