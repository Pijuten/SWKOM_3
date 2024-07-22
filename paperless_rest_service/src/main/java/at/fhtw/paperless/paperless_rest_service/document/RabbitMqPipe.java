package at.fhtw.paperless.paperless_rest_service.document;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class RabbitMqPipe implements Pipe {
    private final static String QUEUE_NAME = "ocr_queue";
    ConnectionFactory connectionFactory;
    public RabbitMqPipe() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }
    @Override
    public boolean process(String input) {
        try (Connection connection = connectionFactory.newConnection()){
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Path path = Paths.get(input);
            String fileName = path.getFileName().toString();
            channel.basicPublish("", QUEUE_NAME, null, fileName.getBytes());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
