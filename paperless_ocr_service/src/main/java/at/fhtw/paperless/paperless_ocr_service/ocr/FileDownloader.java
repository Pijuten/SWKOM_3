package at.fhtw.paperless.paperless_ocr_service.ocr;

import java.io.File;

public interface FileDownloader {
    File download(String fileName);
}
