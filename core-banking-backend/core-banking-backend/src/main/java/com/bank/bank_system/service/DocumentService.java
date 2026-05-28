package com.bank.bank_system.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bank.bank_system.model.Document;
import com.bank.bank_system.repository.DocumentRepository;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    private final String UPLOAD_DIR = System.getProperty("user.home") + "/bank-uploads/";

    public String uploadDocument(String customerId, MultipartFile file) throws IOException {

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        Document doc = new Document();
        doc.setCustomerId(customerId);
        doc.setFileName(file.getOriginalFilename());
        doc.setFileType(file.getContentType());
        doc.setFilePath(filePath);

        documentRepository.save(doc);

        return "File uploaded successfully: " + file.getOriginalFilename();
    }

    public List<Document> getDocumentsByCustomerId(String customerId) {
        return documentRepository.findByCustomerId(customerId);
    }
}