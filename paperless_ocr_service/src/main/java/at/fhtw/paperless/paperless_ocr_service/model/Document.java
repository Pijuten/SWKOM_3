package at.fhtw.paperless.paperless_ocr_service.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@org.springframework.data.elasticsearch.annotations.Document(indexName = "document")
public class Document {
    @Id
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String title;
    private String correspondent;
    private String documentType;
    private String tags;
    private String archiveStorageNumber;

    public Document() {

    }
}
