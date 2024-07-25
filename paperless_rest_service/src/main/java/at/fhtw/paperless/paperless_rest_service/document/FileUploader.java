package at.fhtw.paperless.paperless_rest_service.document;


import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    boolean upload(MultipartFile file);
}

