package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void basicOperations() {
        assertEquals(5.0, Calculator.add(2, 3));
        assertEquals(-1.0, Calculator.subtract(2, 3));
        assertEquals(6.0, Calculator.multiply(2, 3));
        assertEquals(2.5, Calculator.divide(5, 2));
        assertEquals(3.0, Calculator.sqrt(9));
    }

    @Test
    void divideByZero() {
        assertThrows(ArithmeticException.class, () -> Calculator.divide(1, 0));
    }

    @Test
    void sqrtOfNegative() {
        assertThrows(InvalidInputException.class, () -> Calculator.sqrt(-4));
    }

    @Test
    void nanAndInfinity() {
        assertThrows(InvalidInputException.class, () -> Calculator.add(Double.NaN, 1));
        assertThrows(InvalidInputException.class, () -> Calculator.multiply(Double.POSITIVE_INFINITY, 2));
    }
}
