package com.example.SwiftCash.controller;

import com.example.SwiftCash.model.dto.TransactionDTO;
import com.example.SwiftCash.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
           this.transactionService = transactionService;
    }

   @PostMapping(value = "/{userId}/create-transaction")
   public ResponseEntity<String> createTransaction(@PathVariable Long userId, @RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.createTransaction(userId, transactionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("OK!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
   }
}
