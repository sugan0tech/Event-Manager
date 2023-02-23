package in.ac.skcet.event_manager.advice;

import in.ac.skcet.event_manager.exception.studentnotfoundexception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.MalformedParametersException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(studentnotfoundexception.class)
    public Map<String,String> handlexception(studentnotfoundexception ex)
    {
        Map<String,String>map=new HashMap<>();
        map.put("errormesaage", ex.getMessage());
        return map;
    }
}
