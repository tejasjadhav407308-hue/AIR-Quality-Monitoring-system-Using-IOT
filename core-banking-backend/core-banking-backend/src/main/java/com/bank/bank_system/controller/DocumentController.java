package com.bank.bank_system.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bank.bank_system.model.Document;
import com.bank.bank_system.service.DocumentService;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload/{customerId}")
    public String uploadDocument(
            @PathVariable String customerId,
            @RequestParam("file") MultipartFile file) {

        try {
            return documentService.uploadDocument(customerId, file);
        } catch (IOException e) {
            return "Upload failed: " + e.getMessage();
        }
    }

    
    @GetMapping("/customer/{customerId}")
    public List<Document> getCustomerDocuments(@PathVariable String customerId) {
        return documentService.getDocumentsByCustomerId(customerId);
    }
}