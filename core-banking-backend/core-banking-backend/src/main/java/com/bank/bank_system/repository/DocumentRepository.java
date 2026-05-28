package com.bank.bank_system.repository;

import com.bank.bank_system.model.Document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCustomerId(String customerId);
}