package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
     static void main(String[] args) {

        try (Scanner scanner =  new Scanner(System.in).useLocale(Locale.US)) {
            System.out.print("Операція (+, -, *, /, sqrt): ");
            String op = scanner.next().toLowerCase();
            if (!List.of("+", "-", "*", "/", "sqrt").contains(op)) {
                throw new InvalidInputException("Невідома операція: " + op);
            }

            System.out.print("Перше число: ");
            double a = scanner.nextDouble();

            double b = 0;
            if (!op.equals("sqrt")) {
                System.out.print("Друге число: ");
                b = scanner.nextDouble();
            }

            double result = switch (op) {
                case "+" -> Calculator.add(a, b);
                case "-" -> Calculator.subtract(a, b);
                case "*" -> Calculator.multiply(a, b);
                case "/" -> Calculator.divide(a, b);
                case "sqrt" -> Calculator.sqrt(a);
                default -> throw new InvalidInputException("Невідома операція: " + op);
            };

            System.out.println("Результат: " + result);
        } catch (InputMismatchException e) {
            System.out.println("Помилка: потрібно ввести число");
        } catch (ArithmeticException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Некоректні дані: " + e.getMessage());
        } finally {
            System.out.println("Обробку запиту завершено");
        }
    }
}