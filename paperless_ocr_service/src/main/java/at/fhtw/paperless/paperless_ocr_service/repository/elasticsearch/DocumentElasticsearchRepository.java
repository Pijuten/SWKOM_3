package at.fhtw.paperless.paperless_ocr_service.repository.elasticsearch;

import at.fhtw.paperless.paperless_ocr_service.model.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DocumentElasticsearchRepository extends ElasticsearchRepository<Document, Long> {
}
