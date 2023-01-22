package in.ac.skcet.event_manager.controllers.apis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis")
@Slf4j
public class TestApi {
    @PostMapping({"/{id}"})
    public String testApi(@PathVariable String id){
        log.info(id);
        return id;
    }
}
