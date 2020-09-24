package com.mastercard.excercise.citiconnect.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastercard.excercise.citiconnect.constants.ApiConstants;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CitiConnectControllerTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	
	@Test
    public void connected_cities_IT() {

        Map<String, String> params = new HashMap<>();
        params.put("origin", "Boston");
        params.put("destination", "New York");

        String searchConnection = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assertions.assertEquals(ApiConstants.YES, searchConnection);
    }
	
	@Test
    public void connected_adjacent_cities_IT() {

        Map<String, String> params = new HashMap<>();
        params.put("origin", "Boston");
        params.put("destination", "Newark");

        String searchConnection = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assertions.assertEquals(ApiConstants.YES, searchConnection);
    }
	
	@Test
    public void disconnected__cities_IT() {

        Map<String, String> params = new HashMap<>();
        params.put("origin", "Boston");
        params.put("destination", "Trenton");

        String searchConnection = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assertions.assertEquals(ApiConstants.NO, searchConnection);
    }
	
	@Test
    public void incorrect_cities_IT() {

        Map<String, String> params = new HashMap<>();
        params.put("origin", "AAA");
        params.put("destination", "BBB");

        String searchConnection = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
        Assertions.assertEquals(ApiConstants.NO, searchConnection);
    }
	
	@Test
    public void incorrect_input_IT() {
        ResponseEntity<String> response  = restTemplate.exchange("/connected?origin=none", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }




}
