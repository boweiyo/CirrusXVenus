package com.axess.smartbankapi.sqs;

import com.axess.smartbankapi.ses.Email;
import com.axess.smartbankapi.ses.EmailService;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;
    @Autowired
    EmailService emailService;

    @SqsListener(value = "${cloud.aws.end-point.uri}",deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(TestMessage message) {
        log.info("Message from SQS {}", message.getMessage());

        Email email = new Email();
        email.setBody(message.getMessage());
        email.setFrom("admin@cloudtech-training.com");
        email.setTo("fionasiah_23@hotmail.com");
        email.setSubject("Smart Bank Order Confirmation");
        emailService.sendEmail(email);
        log.info("sent email to user");
    }
}