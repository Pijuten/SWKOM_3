package at.fhtw.paperless.paperless_rest_service.document;

import at.fhtw.paperless.paperless_rest_service.model.Document;
import at.fhtw.paperless.paperless_rest_service.repository.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentService {
    private final FileUploader fileUploader;
    private final RabbitTemplate rabbitTemplate;
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(FileUploader fileUploader, RabbitTemplate rabbitTemplate, DocumentRepository documentRepository) {
        this.fileUploader = fileUploader;
        this.rabbitTemplate = rabbitTemplate;
        this.documentRepository = documentRepository;
    }

    @Transactional
    public boolean createDocument(Document document) {
        boolean uploadSuccess = fileUploader.upload(document.getDocumentFilePath());
        if (uploadSuccess) {
            // Send a message to RabbitMQ
            try {
                Document savedDocument = documentRepository.save(document);
                Path path = Paths.get(savedDocument.getDocumentFilePath());
                String fileName = path.getFileName().toString();
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, savedDocument.getId()+"_"+fileName);

            }catch (Exception e){
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
