package com.mastercard.excercise.citiconnect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.excercise.citiconnect.constants.ApiConstants;
import com.mastercard.excercise.citiconnect.model.City;
import com.mastercard.excercise.citiconnect.service.ConnectionCalculatorService;
import com.mastercard.excercise.citiconnect.service.DataLoader;

import io.swagger.annotations.ApiParam;

@RestController
public class CitiConnectController {

	@Autowired
	ConnectionCalculatorService connectionCalculatorService;

	@Autowired
	DataLoader dataLoader;

	private static final Logger LOGGER = LoggerFactory.getLogger(CitiConnectController.class);

	/**
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	@GetMapping(value = "/connected", produces = "text/plain")
	public String isConnected(
			@ApiParam(name = "origin", value = "Origin City", required = true) @RequestParam String origin,
			@ApiParam(name = "destination", value = "Destination City", required = true) @RequestParam String destination) {

		LOGGER.info("City origin {}, and destination {} ", origin, destination);

		City originCity = dataLoader.getCity(origin.toUpperCase().trim());
		City destinationCity = dataLoader.getCity(destination.toUpperCase().trim());

		boolean searchConnection = false;

		if (originCity != null && destinationCity != null) {

			searchConnection = connectionCalculatorService.searchConnection(originCity, destinationCity);
		}

		String yesOrNo = searchConnection ? ApiConstants.YES : ApiConstants.NO;

		return yesOrNo;
	}

}
