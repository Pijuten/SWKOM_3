package at.fhtw.paperless.paperless_ocr_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Document {
    @Id
    private Long id;
    private String Content;

    public Document() {

    }
}
