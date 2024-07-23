package at.fhtw.paperless.paperless_ocr_service.ocr;

import at.fhtw.paperless.paperless_ocr_service.model.Document;
import at.fhtw.paperless.paperless_ocr_service.repository.DocumentRepository;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OCRService {
    public final DocumentRepository documentRepository;

    @Autowired
    public OCRService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public String performOCR(File file) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata_best"); // Set the path to tessdata directory
        try {
            return tesseract.doOCR(file);
        } catch (TesseractException e) {
            return null;
        }
    }
    public void updateDocument(Document document) {
        documentRepository.save(document);
    }
}
