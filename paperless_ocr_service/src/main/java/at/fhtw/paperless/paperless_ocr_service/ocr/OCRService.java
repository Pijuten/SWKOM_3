package at.fhtw.paperless.paperless_ocr_service.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OCRService {

    public String performOCR(String documentPath) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/path/to/tessdata"); // Set the path to tessdata directory
        try {
            return tesseract.doOCR(new File(documentPath));
        } catch (TesseractException e) {
            return null;
        }
    }
}
