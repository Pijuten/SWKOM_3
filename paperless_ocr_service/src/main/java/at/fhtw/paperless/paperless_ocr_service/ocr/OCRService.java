package at.fhtw.paperless.paperless_ocr_service.ocr;

import at.fhtw.paperless.paperless_ocr_service.model.Document;
import at.fhtw.paperless.paperless_ocr_service.repository.elasticsearch.DocumentElasticsearchRepository;
import at.fhtw.paperless.paperless_ocr_service.repository.jpa.DocumentJPARepository;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OCRService {

    public final DocumentJPARepository documentJPARepository;
    public final DocumentElasticsearchRepository documentElasticSearchRepository;

    @Autowired
    public OCRService(DocumentJPARepository documentJPARepository, DocumentElasticsearchRepository documentElasticSearchRepository) {
        this.documentJPARepository = documentJPARepository;
        this.documentElasticSearchRepository = documentElasticSearchRepository;
    }

    public String performOCR(File file) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/app/tessdata_best");
        try {
            return tesseract.doOCR(file);
        } catch (TesseractException e) {
            return null;
        }
    }
    public void updateDocument(Document document) {
        Document savedDocument = documentJPARepository.save(document);
        documentElasticSearchRepository.save(savedDocument);
    }
    public Document getDocument(Long id) {
        return documentJPARepository.findById(id).orElse(null);
    }
}
