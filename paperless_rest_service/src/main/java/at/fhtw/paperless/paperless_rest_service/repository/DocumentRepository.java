package at.fhtw.paperless.paperless_rest_service.repository;

import at.fhtw.paperless.paperless_rest_service.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, String> {
}