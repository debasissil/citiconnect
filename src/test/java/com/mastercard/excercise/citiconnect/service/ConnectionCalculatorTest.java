package com.mastercard.excercise.citiconnect.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.mastercard.excercise.citiconnect.model.City;

public class ConnectionCalculatorTest { 
	
	@InjectMocks
	@Spy
	private ConnectionCalculatorImpl mockConnectionCalculator;
	
	@Mock
	private DataLoader dataLoader;
	
	@Mock
	private City city;
	
	private Map<String, City> cityMap = new HashMap<String, City>();
	private City boston;
	private City philly;
	private City newYork;
	private City trenton;
	private City newark;
	private City hamilton;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		// prepare data
		String bostonCity = "boston";
		String philadelphiaCity = "philadelphia";
		String newYorkCity = "new york";
		String trentonCity = "trenton";
		String newarkCity = "newark";
		String hamiltonCity = "hamilton";
		
		boston = new City();
		boston.setName(bostonCity);
		
		philly = new City();
		philly.setName(philadelphiaCity);
		
		newYork= new City();
		newYork.setName(newYorkCity);
		
		trenton = new City();
		trenton.setName(trentonCity);
		
		newark = new City();
		newark.setName(newarkCity);
		
		hamilton = new City();
		hamilton.setName(hamiltonCity);
		
		
		
		boston.addAdjacentCities(philly);
		boston.addAdjacentCities(newYork);
		cityMap.put(bostonCity, boston);
		
		philly.addAdjacentCities(boston);
		philly.addAdjacentCities(trenton);
		cityMap.put(philadelphiaCity, philly);
		
		newYork.addAdjacentCities(boston);
		cityMap.put(newYorkCity, newYork);
		
		
		
		trenton.addAdjacentCities(philly);
		cityMap.put(trentonCity, trenton);
		
		hamilton.addAdjacentCities(newark);
		cityMap.put(hamiltonCity, hamilton);
		
		newark.addAdjacentCities(hamilton);
		cityMap.put(newarkCity, newark);
		
	}

	@Test
	public void test_same_origin_destination() {
		Mockito.when(dataLoader.getCityMap()).thenReturn(cityMap);
		boolean searchConnection = mockConnectionCalculator.searchConnection(boston, boston);
		Assertions.assertEquals(Boolean.TRUE, searchConnection);
	}
	
	@Test
	public void test_diff_origin_destination() {
		Mockito.when(dataLoader.getCityMap()).thenReturn(cityMap);
		boolean searchConnection = mockConnectionCalculator.searchConnection(boston, philly);
		Assertions.assertEquals(Boolean.TRUE, searchConnection);
	}
	
	@Test
	public void test_diff_origin_destination_connected() {
		Mockito.when(dataLoader.getCityMap()).thenReturn(cityMap);
		boolean searchConnection = mockConnectionCalculator.searchConnection(boston, trenton);
		Assertions.assertEquals(Boolean.TRUE, searchConnection);
	}
	
	@Test
	public void test_diff_origin_destination_not_connected() {
		Mockito.when(dataLoader.getCityMap()).thenReturn(cityMap);
		boolean searchConnection = mockConnectionCalculator.searchConnection(boston, newark);
		Assertions.assertEquals(Boolean.FALSE, searchConnection);
	}


}
