package com.axess.smartbankapi.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SQSService {
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    public void sendMessage(String message) {
        log.info("Sending Message to SQS: "+message);
        queueMessagingTemplate.convertAndSend(endpoint, new TestMessage(message));
        log.info("Message sent to SQS: "+message);
    }
}
