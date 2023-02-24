package in.ac.skcet.event_manager.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "/home/sugan/Documents/GitHub/Event-Manager/target/classes/";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}