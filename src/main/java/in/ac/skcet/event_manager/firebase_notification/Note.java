package in.ac.skcet.event_manager.firebase_notification;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Note {
    private String subject;
    private String content;
    private String image;
}