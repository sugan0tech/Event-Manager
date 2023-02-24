package in.ac.skcet.event_manager.teacher;

import in.ac.skcet.event_manager.event.EventService;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
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

    public Teacher findById(String staffId) throws TeacherNotFoundException {
        return teacherRepository.findById(staffId).orElseThrow(() -> new TeacherNotFoundException("Teacher Not found id :" + staffId));
    }

    public List<Event> findEvents(String teacherId){
        String teacherClassCode = teacherRepository.findById(teacherId).orElse(new Teacher()).getClassCode();
        return eventService.findAll().stream().filter(event -> event.getClassCode().equals(teacherClassCode)).collect(Collectors.toList());
    }
    public Set<Teacher> findByClassCode(String classCode){
        return teacherRepository.findByClassCode(classCode);
    }

}