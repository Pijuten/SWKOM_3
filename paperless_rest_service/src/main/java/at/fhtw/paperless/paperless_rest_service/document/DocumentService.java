package at.fhtw.paperless.paperless_rest_service.document;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final FileUploader fileUploader;
    private final Pipe pipe;

    public DocumentService(FileUploader fileUploader, Pipe pipe) {
        this.fileUploader = fileUploader;
        this.pipe = pipe;
    }
    public boolean createDocument(String filePath) {
        return fileUploader.upload(filePath) && pipe.process(filePath);
    }
}
