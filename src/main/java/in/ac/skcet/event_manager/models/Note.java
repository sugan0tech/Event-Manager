package in.ac.skcet.event_manager.models;

import lombok.Data;

import java.util.Map;

@Data
public class Note {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;
}