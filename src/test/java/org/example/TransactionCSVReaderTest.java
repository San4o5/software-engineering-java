package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;

public class TransactionCSVReaderTest {

    @Test
    public void testReadTransactionsFromCSV(){
        URL url = getClass().getResource("/test_transactions.csv");
        Assertions.assertNotNull(url, "Тестовий CSV-файл не знайдено");

        TransactionCSVReader reader = new TransactionCSVReader();
        List<Transaction> transactions = reader.readTransactions(url.toExternalForm());

        Assertions.assertEquals(3, transactions.size(), "Кількість транзакцій з CSV неправильна");

        Transaction first = transactions.get(0);
        Assertions.assertEquals("15-01-2024", first.getDate(), "Дата першої транзакції неправильна");
        Assertions.assertEquals(-1200.0, first.getAmount(), "Сума першої транзакції неправильна");
        Assertions.assertEquals("Оренда квартири", first.getDescription(), "Опис першої транзакції неправильний");

        Transaction second = transactions.get(1);
        Assertions.assertEquals(-350.0, second.getAmount(), "Сума другої транзакції неправильна");

        Transaction third = transactions.get(2);
        Assertions.assertEquals(10000.0, third.getAmount(), "Сума третьої транзакції неправильна");
    }
}