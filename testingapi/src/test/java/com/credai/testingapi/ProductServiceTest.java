package com.credai.testingapi;

import static org.mockito.Mockito.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


//https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#39


public class ProductServiceTest {

    @Mock
    private ProductRepository repository;  // Mocked dependency

    private ProductService service;

    @BeforeEach
    public void setUp() {
        // Initialize mock objects
        MockitoAnnotations.openMocks(this);
        
        // Create an instance of the service with the mocked repository
        service = new ProductService(repository);
    }

    @Test
    public void testCalculateTotalPrice() {
        // Arrange
        Product mockProduct = new Product(1, "Test Product", 20.0);
        
        // Stubbing: Define behavior of mocked repository
        when(repository.getProductById(1)).thenReturn(mockProduct);

        // Act
        double totalPrice = service.calculateTotalPrice(1, 5);
        
        // Assert
        assertEquals(100.0, totalPrice);  // 20 * 5 = 100
    }
    
    @Test
    public void testCalculateTotalPrice_EmptyQuantity() {
        // Arrange
        Product mockProduct = new Product(1, "Test Product", 20.0);
        when(repository.getProductById(1)).thenReturn(mockProduct);

        // Act
        double totalPrice = service.calculateTotalPrice(1, 0);

        // Assert
        assertEquals(0.0, totalPrice);  // 20 * 0 = 0
    }

    @Test
    public void testVerifyMethodCall() {
        // Arrange
        Product mockProduct = new Product(1, "Test Product", 20.0);
        when(repository.getProductById(1)).thenReturn(mockProduct);

        // Act
        service.calculateTotalPrice(1, 5);

        // Assert: Verify that the repository's getProductById() method was called exactly once
        verify(repository, times(1)).getProductById(1);
    }
    
    @Test
    public void testMockList1() {
        
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
        System.out.println(mockedList.get(1));
        
        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed.
        verify(mockedList).get(0);  
    }
    
    @Test
    public void testMockList2(){
        LinkedList<String> mockedList = mock(LinkedList.class);

        
    	mockedList.add("once");

    	 mockedList.add("twice");
    	 mockedList.add("twice");

    	 mockedList.add("three times");
    	 mockedList.add("three times");
    	 mockedList.add("three times");

    	 //following two verifications work exactly the same - times(1) is used by default
    	 verify(mockedList).add("once");
    	 verify(mockedList, times(1)).add("once");

    	 //exact number of invocations verification
    	 verify(mockedList, times(2)).add("twice");
    	 verify(mockedList, times(3)).add("three times");

    	 //verification using never(). never() is an alias to times(0)
    	 verify(mockedList, never()).add("never happened");

    	 //verification using atLeast()/atMost()
    	 verify(mockedList, atMostOnce()).add("once");
    	 verify(mockedList, atLeastOnce()).add("three times");
    	 verify(mockedList, atLeast(2)).add("three times");
    	 verify(mockedList, atMost(5)).add("three times");
    }
    
    @Test
    public void testMockList3() {
    	
    }


}
