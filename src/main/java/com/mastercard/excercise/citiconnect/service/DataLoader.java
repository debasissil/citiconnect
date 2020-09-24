package com.mastercard.excercise.citiconnect.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 
 * This class loads all the data from property file and
 * maps based on the city already has connections.
 * @author debas
 *
 */
@Component
public class DataLoader implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);
	private Map<String, City> cityMap = new HashMap<>();

	@Value("${refdata:classpath:cities.txt}")
	private String cityData;

	@Autowired
	private ResourceLoader resourceLoader;

	public Map<String, City> getCityMap() {
		return cityMap;
	}

	public City getCity(String name) {
		return cityMap.get(name);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		LOGGER.info("Loading data");

		Resource resource = resourceLoader.getResource(cityData);

		InputStream inputStream = resource.getInputStream();

		Scanner scanner = new Scanner(inputStream);

		while (scanner.hasNext()) {

			String line = scanner.nextLine();
			if (StringUtils.isEmpty(line))
				continue;

			LOGGER.info(line);

			String[] split = line.split(",");
			String leftkey = split[0].trim().toUpperCase();
			String rightkey = split[1].trim().toUpperCase();

			if (!leftkey.equals(rightkey)) {
				City left = cityMap.getOrDefault(leftkey, new City(leftkey));
				City right = cityMap.getOrDefault(rightkey, new City(rightkey));

				left.addAdjacentCities(right);
				right.addAdjacentCities(left);

				cityMap.put(left.getName(), left);
				cityMap.put(right.getName(), right);
			}
		}

		LOGGER.info("Map: " + cityMap);

	}

}
