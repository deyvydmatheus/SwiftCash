package com.example.SwiftCash.service;

import com.example.SwiftCash.model.Transaction;
import com.example.SwiftCash.model.User;
import com.example.SwiftCash.model.dto.TransactionDTO;
import com.example.SwiftCash.repositories.ITransactionRepository;
import com.example.SwiftCash.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService{

    private final ITransactionRepository iTransactionRepository;
    private final IUserRepository iUserRepository;
    private final UserService userService;

    @Autowired
    public TransactionService(ITransactionRepository iTransactionRepository, IUserRepository iUserRepository, UserService userService) {
        this.iTransactionRepository = iTransactionRepository;
        this.iUserRepository = iUserRepository;
        this.userService = userService;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return iTransactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return iTransactionRepository.findById(id);
    }

    @Override
    public void validateTransaction(BigDecimal newAmount) {
        if (newAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The amount must be greater than zero");
        }
    }

    @Override
    public void deleteTransaction(Long id) {
        iTransactionRepository.deleteById(id);
    }

    @Override
    public Transaction createTransaction(Long userId, TransactionDTO transactionDTO) {
        LocalDate localDate = LocalDate.now();

        Optional<User> userOptional = iUserRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User existingUser = userOptional.get();

        Transaction transaction = new Transaction();
        transaction.setUser(existingUser);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTimestamp(localDate.atStartOfDay());

        BigDecimal newBalance = existingUser.getBalance().add(transactionDTO.getAmount());
        existingUser.setBalance(newBalance);

        iUserRepository.save(existingUser);

        return iTransactionRepository.save(transaction);
    }





}
