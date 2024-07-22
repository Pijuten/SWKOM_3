package at.fhtw.paperless.paperless_rest_service.document;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final FileUploader fileUploader;
    private final PipeOut pipeOut;

    public DocumentService(FileUploader fileUploader, PipeOut pipeOut) {
        this.fileUploader = fileUploader;
        this.pipeOut = pipeOut;
    }
    public boolean createDocument(String filePath) {
        return fileUploader.upload(filePath) && pipeOut.process(filePath);
    }
}
