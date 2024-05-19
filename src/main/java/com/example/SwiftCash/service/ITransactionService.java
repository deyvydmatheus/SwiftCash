package com.example.SwiftCash.service;

import com.example.SwiftCash.model.Transaction;
import com.example.SwiftCash.model.User;
import com.example.SwiftCash.model.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public interface ITransactionService {

    List<Transaction> getAllTransactions();

    Optional<Transaction> getTransactionById(Long id);

    void validateTransaction(BigDecimal newAmount);

    void deleteTransaction(Long id);

    Transaction createTransaction(Long userId, TransactionDTO transactionDTO);
}
