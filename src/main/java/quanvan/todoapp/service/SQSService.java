package quanvan.todoapp.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SQSService {

    private final AmazonSQS amazonSQS;

    @Value("${aws.sqs.queueUrl}")
    private String queueUrl;

    public SQSService(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    public void sendMessageToSQS(String queueName, String message) {
        String customQueueUrl = queueUrl + "/" + queueName;
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(customQueueUrl)
                .withMessageBody(message);
        amazonSQS.sendMessage(request);
    }

    public void consumeMessages(String queueName) {
        String fullQueueUrl = queueUrl + "/" + queueName;
        while (true) {
            List<Message> messages = amazonSQS.receiveMessage(fullQueueUrl).getMessages();
            for (Message message : messages) {
                String body = message.getBody();
                // Your message processing logic goes here
                // Delete the message from the queue after processing
                amazonSQS.deleteMessage(fullQueueUrl, message.getReceiptHandle());
            }
        }
    }
}
