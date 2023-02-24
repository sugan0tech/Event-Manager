package in.ac.skcet.event_manager.advice;

import in.ac.skcet.event_manager.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StudentNotFoundException.class)
    public Map<String,String> handleException(StudentNotFoundException ex)
    {
        Map<String,String>map=new HashMap<>();
        map.put("error message", ex.getMessage());
        return map;
    }
}
