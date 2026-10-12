package org.example;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance){
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count){
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printExpenseRangeReport(String startDate, String endDate, TransactionAnalyzer.ExpenseRange range) {
        System.out.println("Витрати за період " + startDate + " - " + endDate + ":");
        if (range.highest() == null && range.lowest() == null) {
            System.out.println("Витрат за вказаний період не знайдено");
            return;
        }
        System.out.println("Найбільша витрата: " + range.highest().getDescription() + ": " + range.highest().getAmount());
        System.out.println("Найменша витрата: " + range.lowest().getDescription() + ": " + range.lowest().getAmount());
    }

    public static void printExpensesByCategory(List<Transaction> transactions) {
        System.out.println("Витрати по категоріях (1 * = 100 грн):");
        printChart(transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(Transaction::getDescription, TreeMap::new,
                        Collectors.summingDouble(t -> -t.getAmount()))));
    }

    public static void printExpensesByMonth(List<Transaction> transactions) {
        System.out.println("Витрати по місяцях (1 * = 100 грн):");
        printChart(transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(t -> t.getDate().substring(6) + "-" + t.getDate().substring(3, 5),
                        TreeMap::new, Collectors.summingDouble(t -> -t.getAmount()))));
    }

    private static void printChart(Map<String, Double> data) {
        data.forEach((name, sum) -> System.out.println(name + ": " + "*".repeat((int) (sum / 100)) + " " + sum));
    }

}
