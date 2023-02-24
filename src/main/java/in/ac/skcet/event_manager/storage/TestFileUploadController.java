package in.ac.skcet.event_manager.storage;

import in.ac.skcet.event_manager.storage.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Slf4j
public class TestFileUploadController {
    private StorageService storageService;

    @PostMapping("/store")
    public String handleFileUpload(@RequestParam("file")  MultipartFile file[]
                                   ) {
        log.info(file[0].getOriginalFilename());
        log.info(file[0].isEmpty() + " ");

        storageService.store(file[0]);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "Done!";
    }
}
