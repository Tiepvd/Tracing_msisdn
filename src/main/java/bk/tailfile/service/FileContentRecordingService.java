package bk.tailfile.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class FileContentRecordingService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private Logger logger = Logger.getLogger(FileContentRecordingService.class);

    public void sendLinesToTopic(String line) {
        System.out.println(line);
        String[] lines = line.split("##");
        for (String line1 : lines) {
            line = line1;
        }
        this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", line);
    }
}
