package in.ac.skcet.event_manager.advice;

import in.ac.skcet.event_manager.exception.EventNotFoundException;
import in.ac.skcet.event_manager.exception.InvalidDateException;
import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import in.ac.skcet.event_manager.exception.TeacherNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StudentNotFoundException.class)
    public Map<String,String> handleExceptionForStudent(StudentNotFoundException ex)
    {
        Map<String,String>map=new HashMap<>();
        map.put("error message", ex.getMessage());
        return map;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TeacherNotFoundException.class)
    public Map<String,String> handleExceptionForTeacher(TeacherNotFoundException ex)
    {
        Map<String,String>map=new HashMap<>();
        map.put("error message", ex.getMessage());
        return map;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidDateException.class)
    public Map<String,String> handleExceptionForDate(InvalidDateException ex)
    {
        Map<String,String>map=new HashMap<>();
        map.put("error message", ex.getMessage());
        log.info("Invalid Date");
        return map;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EventNotFoundException.class)
    public Map<String,String> handleExceptionForEvent(InvalidDateException ex)
    {
        Map<String,String>map=new HashMap<>();
        map.put("error message", ex.getMessage());
        log.info("Invalid Date");
        return map;
    }

}
