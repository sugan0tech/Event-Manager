package in.ac.skcet.event_manager.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String getIndexPage(){
        return "Demo Api's Will be available at /apis";
    }

}
