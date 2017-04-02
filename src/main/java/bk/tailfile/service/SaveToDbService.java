package bk.tailfile.service;

import org.springframework.stereotype.Service;

/**
 * Created by Vu Duc Tiep on 3/19/2017.
 */
@Service
public class SaveToDbService {
    public void sendLinesToTopic(String line) {
        System.out.println(line);
        String[] lines = line.split("##");
        for (String line1 : lines) {
            line = line1;
        }

        lines = line.split("\\|");
        for (String line1 : lines) {
            System.out.printf(line1);
        }

    }
}
