package at.fhtw.paperless.paperless_rest_service.document;

import at.fhtw.paperless.paperless_rest_service.model.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
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
    public ResponseEntity<String> createDocument(@RequestBody Document document) {
        if(documentService.createDocument(document)) {
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
