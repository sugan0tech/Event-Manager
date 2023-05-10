package in.ac.skcet.event_manager.firebase_notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import in.ac.skcet.event_manager.attendance.Attendance;
import in.ac.skcet.event_manager.event.Event;
import in.ac.skcet.event_manager.event.EventStatService;
import in.ac.skcet.event_manager.student.Student;
import in.ac.skcet.event_manager.student.StudentService;
import in.ac.skcet.event_manager.teacher.Teacher;
import in.ac.skcet.event_manager.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class PushNotificationService {
   FirebaseMessagingService firebaseMessagingService;
   StudentService studentService;
   RegisteredUserService registeredUserService;
   TeacherService teacherService;
   EventStatService eventStatService;


   public void eventCompletionNotification(Event event) throws FirebaseMessagingException{
       Set<Teacher> teacherSet = teacherService.findByClassCode(event.getClassCode());
       Map<String, Integer> stats = eventStatService.getEventStat(event.getEventId(), event.getClassCode());
       for(Teacher teacher : teacherSet){
           String token = registeredUserService.getTokenByEmail(teacher.getMail()).orElse(null);
           if(token != null){
               Note note = Note.builder()
                        .content("pending -" + stats.get("pending") + "\ncompleted -" + stats.get("completed"))
                       .subject("Event Ended { " + event.getTitle() + " }")
                       .build();

               log.info(teacher.getMail() + " - token :" +  token);
               firebaseMessagingService.sendNotificationByToken(note, token);
           }
       }
   }

   public void attendanceNotificationPerStaff(Teacher teacher, Attendance attendance) throws FirebaseMessagingException{

       List<Student> studentSet = studentService.findByClassCode(teacher.getClassCode());

       int total = studentSet.size();
       int present = (int) studentSet.stream().filter(student -> {
           if (student.getAttendanceBitSetMap().containsKey(attendance)){
               return !student.getAttendanceBitSetMap().get(attendance).isEmpty();
           }
           return false;
       }).count();
       int absent = total - present;
       int od = (int) studentSet.stream().filter(student -> student.getOnDuty() != null).count();

           String token = registeredUserService.getTokenByEmail(teacher.getMail()).orElse(null);
           if(token != null){
               Note note = Note.builder()
                       .content("present - " + present + "/" + total+ "\nabsent - " + absent + " od - " + od )
                       .subject(teacher.getClassCode() + " " + "Attendance" + " By - " + teacher.getName())
                       .build();
               firebaseMessagingService.sendNotificationByToken(note, token);
           }

   }

    public void attendanceNotification(String classCode, Attendance attendance) throws FirebaseMessagingException{
        Set<Teacher> teacherSet = teacherService.findByClassCodeExact(classCode);
        List<Student> studentSet = studentService.findByClassCode(classCode);

        int total = studentSet.size();
        int present = (int) studentSet.stream().filter(student -> {
            if (student.getAttendanceBitSetMap().containsKey(attendance)){
                return !student.getAttendanceBitSetMap().get(attendance).isEmpty();
            }
            return false;
        }).count();
        log.info("Present" + present);
        int absent = total - present;
        log.info(studentSet.toString());
        int od = (int) studentSet.stream().filter(student -> student.getOnDuty() != null).count();

        for(Teacher teacher : teacherSet){
            String token = registeredUserService.getTokenByEmail(teacher.getMail()).orElse(null);
            if(token != null){
                Note note = Note.builder()
                        .content("present - " + present + "/" + total+ "\nabsent - " + absent + " od - " + od)
                        .subject(classCode + " " + "Attendance")
                        .build();

                log.info(token);
                firebaseMessagingService.sendNotificationByToken(note, token);
            }
        }
    }

    public void eventNotification(Event event) throws FirebaseMessagingException {
        List<Student> studnetSet = studentService.findByClassCode(event.getClassCode());
        for(Student student : studnetSet){
            String token = registeredUserService.getTokenByEmail(student.getMail()).orElse(null);
            if(token != null){
                Note note = Note.builder()
                        .content(event.getTitle())
                        .subject("New event alert!!!")
                        .build();

                log.info(token);
                firebaseMessagingService.sendNotificationByToken(note, token);
            }
        }
    }
    public void spam(String token) throws FirebaseMessagingException {
                Note note = Note.builder()
                        .content("Test")
                        .subject("New event alert!!!")
                        .build();

                log.info(token);
                firebaseMessagingService.sendNotificationByToken(note, token);
    }

}
