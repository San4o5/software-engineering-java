package org.example;

import java.util.List;

public interface TransactionReader {
    // Повертає список транзакцій із вказаного джерела
    List<Transaction> readTransactions(String source);
}
