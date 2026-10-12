package org.example;

import java.util.List;

import static org.example.TransactionAnalyzer.*;
import static org.example.TransactionReportGenerator.*;

public class Main {
    public static void main(String[] args) {
        // Читання транзакцій із CSV-файлу
        String filePath = Main.class.getResource("/PR2.csv").toExternalForm();
        TransactionReader reader = new TransactionCSVReader();
        List<Transaction> transactions = reader.readTransactions(filePath);

        // Загальний баланс
        double totalBalance = calculateTotalBalance(transactions);
        printBalanceReport(totalBalance);

        // Кількість транзакцій за місяць
        String monthYear = "01-2024";
        int transactionsCount = countTransactionsByMonth(transactions,monthYear);
        printTransactionsCountByMonth(monthYear,transactionsCount);

        // Топ витрат
        List<Transaction> topExpenses = findTopExpenses(transactions);
        printTopExpensesReport(topExpenses);

        // Найбільша та найменша витрати за період
        String startDate = "01-12-2023";
        String endDate = "31-12-2023";
        ExpenseRange expenseRange = findHighestAndLowestExpenses(transactions, startDate, endDate);
        printExpenseRangeReport(startDate, endDate, expenseRange);

        // Звіти по категоріях та місяцях
        printExpensesByCategory(transactions);
        printExpensesByMonth(transactions);

    }
}
