package in.ac.skcet.event_manager.services;

import in.ac.skcet.event_manager.models.RegisteredUser;
import in.ac.skcet.event_manager.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class RegisteredUserService {
    RegisteredUserRepository registeredUserRepository;

    public RegisteredUser findById(String email){
        return registeredUserRepository.findById(email).orElse(null);
    }

    public RegisteredUser save(RegisteredUser registeredUser){
        return registeredUserRepository.save(registeredUser);
    }

    public Optional<String> getTokenByEmail(String email){
        RegisteredUser registeredUser= registeredUserRepository.findById(email).orElse(null);
        if(registeredUser == null){
            log.info(email + " - Not Registered");
            return Optional.empty();
        }
        return Optional.of(registeredUser.getToken());
    }
}
