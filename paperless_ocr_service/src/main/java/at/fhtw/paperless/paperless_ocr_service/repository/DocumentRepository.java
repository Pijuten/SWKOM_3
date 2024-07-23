package at.fhtw.paperless.paperless_ocr_service.repository;

import at.fhtw.paperless.paperless_ocr_service.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
