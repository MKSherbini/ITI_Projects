import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    private static Calculator calculator;

    @org.junit.Before
    public void setUp() {
        calculator = new Calculator();
    }

    @org.junit.After
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testAdding() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals(-1, calculator.subtract(2, 3));
    }

    @Test
    public void testDivide() {
        assertEquals(0, calculator.divide(2, 3));
    }

    @Test
    public void testDivByZero() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(5, 0);
        });
        assertEquals("Cannot division by zero", exception.getMessage());
    }

}