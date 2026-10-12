package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.TransactionAnalyzer.calculateTotalBalance;
import static org.example.TransactionAnalyzer.countTransactionsByMonth;
import static org.example.TransactionAnalyzer.findHighestAndLowestExpenses;
import static org.example.TransactionAnalyzer.findTopExpenses;
import static org.example.TransactionAnalyzer.ExpenseRange;

public class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance(){
        // Створення тестових даних
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1,transaction2,transaction3);

        // Створення екземпляру TransactionAnalyzer з тестовими даними
        //TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        // Виклик методу, який потрібно протестувати
        double result = calculateTotalBalance(transactions);

        // Перевірка результату
        Assertions.assertEquals(200.0,result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth(){
        Transaction transaction1 = new Transaction("01-02-2023",50.0,"Дохід");
        Transaction transaction2 = new Transaction("15-02-2023",-20.0,"Витрата");
        Transaction transaction3 = new Transaction("05-03-2023",100.0,"Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1,transaction2,transaction3);

        //TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        int countFeb = countTransactionsByMonth(transactions,"02-2023");
        int countMar = countTransactionsByMonth(transactions,"03-2023");

        Assertions.assertEquals(2,countFeb,"Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1,countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testFindTopExpenses(){
        // 13 витрат з різними сумами та 2 доходи
        List<Transaction> transactions = new ArrayList<>(Arrays.asList(
                new Transaction("01-01-2023", -10.0, "Витрата 1"),
                new Transaction("02-01-2023", -202.0, "Витрата 2"),
                new Transaction("03-01-2023", -305.0, "Витрата 3"),
                new Transaction("04-01-2023", -40.0, "Витрата 4"),
                new Transaction("05-01-2023", -80.0, "Витрата 5"),
                new Transaction("06-01-2023", -605.0, "Витрата 6"),
                new Transaction("07-01-2023", -900.0, "Витрата 7"),
                new Transaction("08-01-2023", -80.0, "Витрата 8"),
                new Transaction("09-01-2023", -930.0, "Витрата 9"),
                new Transaction("10-01-2023", -100.0, "Витрата 10"),
                new Transaction("11-01-2023", -110.0, "Витрата 11"),
                new Transaction("12-01-2023", -120.0, "Витрата 12"),
                new Transaction("13-01-2023", -220.0, "Витрата 13"),
                new Transaction("14-01-2023", 500.0, "Дохід 1"),
                new Transaction("15-01-2023", 300.0, "Дохід 2")
        ));

        List<Transaction> topExpenses = findTopExpenses(transactions);

        // Очікується лише 10 найбільших витрат, відсортованих за зростанням суми
        Assertions.assertEquals(10, topExpenses.size(), "Повинно бути рівно 10 найбільших витрат");
        Assertions.assertTrue(topExpenses.stream().allMatch(t -> t.getAmount() < 0), "У результаті не повинно бути доходів");
        Assertions.assertEquals(-930.0, topExpenses.get(0).getAmount(), "Першою має бути найбільша витрата");
        Assertions.assertEquals(-80.0, topExpenses.get(9).getAmount(), "Останньою має бути 10-та найбільша витрата");
        Assertions.assertEquals(
                Arrays.asList(-930.0, -900.0, -605.0, -305.0, -220.0, -202.0, -120.0, -110.0, -100.0, -80.0),
                topExpenses.stream().map(Transaction::getAmount).collect(Collectors.toList()),
                "Порядок 10 найбільших витрат неправильний");
    }

    @Test
    public void testFindHighestAndLowestExpenses(){
        Transaction big = new Transaction("01-03-2023", -500.0, "Витрата 1");   // початок періоду
        Transaction mid = new Transaction("15-03-2023", -120.0, "Витрата 2");
        Transaction small = new Transaction("31-03-2023", -5.0, "Витрата 3");   // кінець періоду
        Transaction income = new Transaction("10-03-2023", 1000.0, "Дохід");    // дохід, ігнорується
        Transaction before = new Transaction("28-02-2023", -9999.0, "До періоду");
        Transaction after = new Transaction("01-04-2023", -1.0, "Після періоду");
        List<Transaction> transactions = Arrays.asList(mid, before, big, income, after, small);

        ExpenseRange result = findHighestAndLowestExpenses(transactions, "01-03-2023", "31-03-2023");

        Assertions.assertSame(big, result.highest(), "Найбільша витрата визначена неправильно");
        Assertions.assertSame(small, result.lowest(), "Найменша витрата визначена неправильно");
    }
}
