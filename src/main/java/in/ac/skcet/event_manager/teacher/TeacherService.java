package in.ac.skcet.event_manager.teacher;

import in.ac.skcet.event_manager.class_code.ClassCodeService;
import in.ac.skcet.event_manager.event.EventService;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class TeacherService {

    TeacherRepository teacherRepository;
    EventService eventService;
    ClassCodeService classCodeService;

    public Teacher findById(String staffId) throws TeacherNotFoundException {
        return teacherRepository.findById(staffId).orElseThrow(() -> new TeacherNotFoundException("Teacher Not found id :" + staffId));
    }

    public List<Event> findEvents(String teacherId){
        String teacherClassCode = teacherRepository.findById(teacherId).orElse(new Teacher()).getClassCode();
        return eventService.findAll().stream().filter(event -> classCodeService.compareCodes(event.getClassCode(), teacherClassCode)&&(event.getEndDate().compareTo(new Date()) >= 0)).collect(Collectors.toList());
    }
    public Set<Teacher> findByClassCode(String classCode){
        List<Teacher> teacherSet = teacherRepository.findAll();
        return teacherSet.stream().filter(teacher ->
            classCodeService.compareCodes(teacher.getClassCode(), classCode)
        ).collect(Collectors.toSet());
    }

    public Set<Teacher> findByClassCodeExact(String classCode){
        return teacherRepository.findByClassCode(classCode);
    }

    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }

}