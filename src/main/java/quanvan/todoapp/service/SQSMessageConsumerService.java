package quanvan.todoapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SQSMessageConsumerService {

    private final SQSService sqsService;

    @Value("${aws.sqs.queueName}")
    private String queueName;

    public SQSMessageConsumerService(SQSService sqsService) {
        this.sqsService = sqsService;
    }

    public void startConsumingMessages() {
        Thread thread = new Thread(() -> sqsService.consumeMessages(queueName));
        thread.start();
    }
}
