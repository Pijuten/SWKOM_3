package at.fhtw.paperless.paperless_rest_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Document {
    @Id
    private Long id;
    private String documentFilePath;
    private String title;
    private String correspondent;
    private String documentType;
    private String storagePath;
    private String tags;
    private String archiveStorageNumber;
}
