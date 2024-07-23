package at.fhtw.paperless.paperless_ocr_service.ocr;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class OCRListener {

    private final OCRService ocrService;
    private final FileDownloader fileDownloader;

    public OCRListener(OCRService ocrService, FileDownloader fileDownloader) {
        this.ocrService = ocrService;
        this.fileDownloader = fileDownloader;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String documentName) {
        System.out.println("Received message: " + documentName);
        String[] parts = documentName.split("_");
        Long documentId = Long.parseLong(parts[0]);
        File  file = fileDownloader.download(parts[1]);
        String ocrResult = ocrService.performOCR(file);
        System.out.println("OCR Result: " + ocrResult);
        // Handle the OCR result (e.g., save to database, send to another service)

    }
}