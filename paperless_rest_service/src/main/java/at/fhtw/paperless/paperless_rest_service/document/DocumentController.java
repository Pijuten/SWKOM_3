package at.fhtw.paperless.paperless_rest_service.document;

import at.fhtw.paperless.paperless_rest_service.model.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;
    private final ObjectMapper objectMapper;

    public DocumentController(DocumentService documentService, ObjectMapper objectMapper) {
        this.documentService = documentService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<String> getDocuments() {

        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public String getDocumentById(@RequestParam String id) {
        return "Hello World";
    }
    @PostMapping("/create")
    public ResponseEntity<String> createDocument(@RequestPart("document") String documentStr, @RequestPart("file") MultipartFile file) {
        Document document = null;
        try {
            document = objectMapper.readValue(documentStr, Document.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(documentService.createDocument(document,file)) {
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error occurred", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update")
    public String updateDocument(@RequestBody Document document) {
        return "Hello World";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteDocumentById(@RequestParam String id) {
        return "Hello World";
    }
}
