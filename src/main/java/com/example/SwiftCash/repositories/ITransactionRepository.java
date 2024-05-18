package com.example.SwiftCash.repositories;


import com.example.SwiftCash.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
}
