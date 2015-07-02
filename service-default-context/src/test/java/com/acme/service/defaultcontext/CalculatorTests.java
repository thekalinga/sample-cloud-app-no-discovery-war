package com.acme.service.defaultcontext;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by thekalinga on 29/6/15.
 */
public class CalculatorTests {

    private Calculator calc;

    @Before
    public void setup() {
        calc = new Calculator();
    }

    @Test
    public void div_ForPositiveDivisor_ShouldSucceed() {
        assertEquals(5, calc.div(10, 2));
        assertEquals(-2, calc.div(-5, 2));
    }

    @Test(expected = ArithmeticException.class)
    public void div_DivideByZero_ShouldThrowDivideByZeroException() {
        calc.div(10, 0);
    }

}
