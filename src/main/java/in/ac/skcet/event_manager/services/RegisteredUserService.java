package in.ac.skcet.event_manager.services;

import in.ac.skcet.event_manager.models.RegisteredUser;
import in.ac.skcet.event_manager.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class RegisteredUserService {
    RegisteredUserRepository registeredUserRepository;

    public RegisteredUser findById(Integer id){
        //TODO Exception needed
        return registeredUserRepository.findById(id).orElse(null);
    }

    public RegisteredUser save(RegisteredUser registeredUser){
        return registeredUserRepository.save(registeredUser);
    }

    public String getTokenByEmail(String email){
        return registeredUserRepository.findByEmail(email).getToken();
    }
}
