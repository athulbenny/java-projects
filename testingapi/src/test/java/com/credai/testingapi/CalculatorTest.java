package com.credai.testingapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		
		int result = calculator.add(2, 3);
		
		assertEquals(5, result, "2+3 should be 5");
	}
	
	@Test
	public void testAddNegativeNumbers() {
		Calculator calculator = new Calculator();
		
		int result = calculator.add(-2, -3);
		
		assertEquals(-5, result, "-2+-3 = 5");
	}
	
	@Test
    public void testAdd2() {
        // Creating a mock object of the Calculator class
        Calculator calculatorMock1 = new Calculator(); 
        Calculator calculatorMock2 = mock(Calculator.class);
        
        // Stubbing the method add to return a specific value when called
        when(calculatorMock2.add(1, 5)).thenReturn(3); // when method only works with method call on mock
//        when(calculatorMock1.add(1, 5)).thenReturn(3); // error since calculatorMock1 is not a mock object
        
        // Calling the stubbed method
        int result = calculatorMock2.add(1, 5);
        
        // Asserting that the stubbed behavior worked
        assertEquals(3, result);
    }
	
	@Test
	public void testException() {
		Calculator calculatorMock2 = mock(Calculator.class);
       
        // Stubbing the method add to return a specific value when called
        when(calculatorMock2.add(0, 0)).thenThrow(new RuntimeException("Error is caught"));
        
        assertThrows(RuntimeException.class,  ()->calculatorMock2.add(0, 0));
	}
	
	@Test
	public void testChainExecution() {
		Calculator calculator = mock(Calculator.class);
		
//		when(calculator.add(4, 5)).thenReturn(5).thenReturn(9);
		
		when(calculator.add(4, 5)).thenReturn(5,9); //alternative method
		
		System.out.println(calculator.add(4, 5)); // output is 5
		
		System.out.println(calculator.add(4, 5)); // output is 9
	}
}
