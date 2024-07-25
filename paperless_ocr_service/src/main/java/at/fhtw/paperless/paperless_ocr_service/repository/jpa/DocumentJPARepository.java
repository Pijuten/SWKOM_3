package at.fhtw.paperless.paperless_ocr_service.repository.jpa;

import at.fhtw.paperless.paperless_ocr_service.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentJPARepository extends JpaRepository<Document, Long> {
}
