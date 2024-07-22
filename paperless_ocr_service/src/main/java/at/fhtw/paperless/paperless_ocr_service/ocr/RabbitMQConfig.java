package at.fhtw.paperless.paperless_ocr_service.ocr;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {
    public static final String QUEUE_NAME = "ocr_queue";

    @Bean
    public Queue rabbitMqPipeIn() {
        return new Queue(QUEUE_NAME, true);
    }
}
