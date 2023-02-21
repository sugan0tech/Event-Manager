package in.ac.skcet.event_manager.controllers.apis;


import in.ac.skcet.event_manager.models.RegisteredUser;
import in.ac.skcet.event_manager.services.RegisteredUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class LoingController {

    RegisteredUserService registeredUserService;

    @RequestMapping("/login/{email}/{token}")
    public String getToken(@PathVariable String email, @PathVariable String token){
        RegisteredUser registeredUser = registeredUserService.findById(email);
        if(registeredUser == null){
            return registeredUserService.save(new RegisteredUser(email, token)).getToken();
        }else{
            registeredUser.setToken(token);
            return registeredUserService.save(registeredUser).getToken();
        }
    }
}
