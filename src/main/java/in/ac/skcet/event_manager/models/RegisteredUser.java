package in.ac.skcet.event_manager.models;

import in.ac.skcet.event_manager.services.RegisteredUserService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@Getter
@Setter
public class RegisteredUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Double id;
    private String email;

    private String token;

    public RegisteredUser(String email, String token) {
        this.email = email;
        this.token = token;
    }

}
