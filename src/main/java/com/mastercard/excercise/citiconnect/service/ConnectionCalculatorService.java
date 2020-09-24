package com.mastercard.excercise.citiconnect.service;

import com.mastercard.excercise.citiconnect.model.City;

/**
 * 
 * @author debas
 *
 */
public interface ConnectionCalculatorService {
	
	/**
	 * This method takes origin and destination and calculates the connectivity.
	 * @param origin
	 * @param destination
	 * @return boolean 
	 */
	public boolean searchConnection(City origin, City destination);

}
