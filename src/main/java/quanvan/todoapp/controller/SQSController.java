package quanvan.todoapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import quanvan.todoapp.service.SQSMessageConsumerService;
import quanvan.todoapp.service.SQSService;

@RestController
@RequestMapping("/sqs")
public class SQSController {

    private final SQSService sqsService;
    private final SQSMessageConsumerService sqsMessageConsumerService;
    @Value("${aws.sqs.queueName}")
    private String queueName;

    public SQSController(SQSService sqsService, SQSMessageConsumerService sqsMessageConsumerService) {
        this.sqsService = sqsService;
        this.sqsMessageConsumerService = sqsMessageConsumerService;
    }

    @PostMapping("/send")
    public String sendMessageToSQS(@RequestBody String message) {
        try {
            sqsService.sendMessageToSQS(queueName, message);
            return "Message sent to SQS successfully.";
        } catch (Exception e) {
            return "Failed to send message to SQS: " + e.getMessage();
        }
    }

    @GetMapping("/consume")
    public String startConsumer() {
        sqsMessageConsumerService.startConsumingMessages();
        return "Message consumer started.";
    }
}

