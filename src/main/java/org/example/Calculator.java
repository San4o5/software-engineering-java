package org.example;

public final class Calculator {

    private static void check(double... values) {
        for (double v : values) {
            if (Double.isNaN(v) || Double.isInfinite(v)) {
                throw new InvalidInputException("Некоректне число: " + v);
            }
        }
    }

    public static double add(double a, double b) {
        check(a, b);
        return a + b;
    }

    public static double subtract(double a, double b) {
        check(a, b);
        return a - b;
    }

    public static double multiply(double a, double b) {
        check(a, b);
        return a * b;
    }

    public static double divide(double a, double b) {
        check(a, b);
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль");
        }
        return a / b;
    }

    public static double sqrt(double a) {
        check(a);
        if (a < 0) {
            throw new InvalidInputException("Корінь з від'ємного числа: " + a);
        }
        return Math.sqrt(a);
    }
}