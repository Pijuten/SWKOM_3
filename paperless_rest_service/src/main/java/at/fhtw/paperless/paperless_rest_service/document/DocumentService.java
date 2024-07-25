package at.fhtw.paperless.paperless_rest_service.document;

import at.fhtw.paperless.paperless_rest_service.model.Document;
import at.fhtw.paperless.paperless_rest_service.repository.DocumentJPARepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class DocumentService {
    private final FileUploader fileUploader;
    private final RabbitTemplate rabbitTemplate;
    private final DocumentJPARepository documentJPARepository;

    @Autowired
    public DocumentService(FileUploader fileUploader, RabbitTemplate rabbitTemplate, DocumentJPARepository documentJPARepository) {
        this.fileUploader = fileUploader;
        this.rabbitTemplate = rabbitTemplate;
        this.documentJPARepository = documentJPARepository;
    }

    public boolean createDocument(Document document, MultipartFile file) {
        // Upload document to Bucket
        boolean uploadSuccess = fileUploader.upload(file);
        if (uploadSuccess) {
            // Send a message to RabbitMQ
            try {
                Document savedDocument = documentJPARepository.save(document);
                Path path = Paths.get(Objects.requireNonNull(file.getOriginalFilename()));
                String fileName = path.getFileName().toString();
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, savedDocument.getId()+"_"+fileName);

            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("File upload failed");
            return false;
        }
        return true;
    }
}
