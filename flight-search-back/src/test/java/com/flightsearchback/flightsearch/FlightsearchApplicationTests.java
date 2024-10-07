package com.flightsearchback.flightsearch;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class FlightsearchApplicationTests {

	@Autowired
	private FlightsearchController flightsearchController;

	@Test
	void contextLoads() {
	}

	@Autowired
	private FlightsearchService flightsearchService;

	@Test
	public void testAuthentication() throws Exception{
		String access_token = flightsearchService.authenticate();
		Assertions.assertThat(access_token).isNotNull();
	}

	@Test
	public void testAirportSearch() throws Exception{
		List<Airport> airportsResponse = flightsearchService.findAirportsByKeyword("MUC");
		assertThat(airportsResponse.get(0)).isNotNull();
	}

	@Test
	public void testFlightsSearch() throws Exception{

		Map<String,String> allParams = new HashMap<>();
		allParams.put("originLocationCode","HMO");
		allParams.put("destinationLocationCode","GDL");
		allParams.put("departureDate","2024-09-28");
		allParams.put("adults","1");
		allParams.put("nonStop","true");
		allParams.put("returnDate","2024-10-12");
		allParams.put("currency", "USD");


		List<TripResult> tripResults = flightsearchService.findFlights(allParams);
		assertThat(tripResults.get(0)).isNotNull();
	}

}
