package com.mastercard.excercise.citiconnect.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mastercard.excercise.citiconnect.model.City;

@Component
public class ConnectionCalculatorImpl implements ConnectionCalculatorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionCalculatorImpl.class);

	@Autowired
	DataLoader dataLoader;

	/**
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public boolean searchConnection(City origin, City destination) {

		if (origin.equals(destination))
			return true;

		if (origin.getAdjacentCities().contains(destination))
			return true;

		boolean connected = connectivtyCheck(origin, destination);
		LOGGER.info("connected : {}", connected);

		return connected;
	}

	/**
	 * This method uses BFS algo.
	 * 
	 * @param origin
	 * @param destination
	 * @return boolean
	 */
	private boolean connectivtyCheck(City origin, City destination) {
		Map<String, City> cities = dataLoader.getCityMap();
		LinkedList<City> queue = new LinkedList<>();
		Set<City> alreadyConnectedCities = new HashSet<>();
		// Mark the current city as connected
		queue.add(origin);
		alreadyConnectedCities.add(origin);

		while (queue.size() != 0) {
			// Dequeue city from queue
			origin = queue.poll();
			if (origin.equals(destination)) {
				return true;
			}

			// Get all adjacent cities of the dequeued city
			// If a adjacent city has not been connected, then enqueue it

			if (cities.get(origin.getName()) != null) {
				for (City city : cities.get(origin.getName()).getAdjacentCities()) {
					if (alreadyConnectedCities.add(city)) {
						queue.add(city);
					}
				}
			}
		}

		return false;
	}

}
