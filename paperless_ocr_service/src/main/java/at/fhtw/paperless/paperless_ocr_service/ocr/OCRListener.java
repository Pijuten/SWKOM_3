package at.fhtw.paperless.paperless_ocr_service.ocr;

import at.fhtw.paperless.paperless_ocr_service.model.Document;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

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
        File file = fileDownloader.download(parts[1]);
        Document document = ocrService.getDocument(documentId);
        if (document != null) {
            document.setContent(performOCR(file));
            ocrService.updateDocument(document);
        } else {
            System.out.println(Arrays.toString(parts));
            System.out.println("Document not found");
        }
        // Handle the OCR result (e.g., save to database, send to another service)
    }

    public String performOCR(File file) {
        return ocrService.performOCR(file);
    }
}