package bk.tailfile.config;

import bk.tailfile.service.FileContentRecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.file.tail.ApacheCommonsFileTailingMessageProducer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class TailFileConfig {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private FileContentRecordingService fileContentRecordingService;

    public MessageProducerSupport fileContentProducer() {
//		String filePath= "/home/firefly/log/10.1.31.21/Log/";
        String filePath = "D:/tmp/";

        ApacheCommonsFileTailingMessageProducer tailFileProducer = new ApacheCommonsFileTailingMessageProducer();

        tailFileProducer.setFile(new File(filePath + "messages-"+simpleDateFormat.format(new Date())+".log"));
        tailFileProducer.setTailAttemptsDelay(10000);
        tailFileProducer.setPollingDelay(2000);
        return tailFileProducer;
    }

    @Bean
    public IntegrationFlow tailFilesFlow() {
        return IntegrationFlows.from(this.fileContentProducer())
                .handle("fileContentRecordingService", "sendLinesToTopic")
                .handle("saveToDbService", "sendLinesToTopic")
                .get();
    }
}
