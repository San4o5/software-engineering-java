package org.example;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class TransactionAnalyzer {
    // Найбільша (highest) та найменша (lowest) витрати за період
    public record ExpenseRange(Transaction highest, Transaction lowest) {}

    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions){
        double balance = 0;
        for (Transaction transaction : transactions){
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Кількість транзакцій за вказаний місяць
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear){
        int count = 0;
        for (Transaction transaction : transactions){
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)){
                count++;
            }
        }
        return count;
    }

    // 10 найбільших витрат за спаданням суми
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Найбільша та найменша витрати у проміжку [startDate, endDate]
    public static ExpenseRange findHighestAndLowestExpenses(List<Transaction> transactions, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DATE_FORMAT);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMAT);
        Comparator<Transaction> byAmount = Comparator.comparingDouble(Transaction::getAmount);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> isInRange(t, start, end))
                .collect(Collectors.teeing(
                        Collectors.minBy(byAmount),   // найбільша витрата (найбільш від'ємна)
                        Collectors.maxBy(byAmount),   // найменша витрата (найближча до нуля)
                        (highest, lowest) -> new ExpenseRange(highest.orElse(null), lowest.orElse(null))
                ));
    }

    // Чи потрапляє дата транзакції у проміжок [start, end]
    private static boolean isInRange(Transaction t, LocalDate start, LocalDate end) {
        LocalDate date = LocalDate.parse(t.getDate(), DATE_FORMAT);
        return !date.isBefore(start) && !date.isAfter(end);
    }

}
