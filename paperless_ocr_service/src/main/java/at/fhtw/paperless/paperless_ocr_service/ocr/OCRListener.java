package at.fhtw.paperless.paperless_ocr_service.ocr;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OCRListener {

    @Autowired
    private OCRService ocrService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String documentPath) {
        System.out.println("Received message: " + documentPath);
        String ocrResult = ocrService.performOCR(documentPath);
        System.out.println("OCR Result: " + ocrResult);
        // Handle the OCR result (e.g., save to database, send to another service)
    }
}