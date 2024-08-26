package com.flightsearchback.flightsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class FlightsearchApplication {


	public static void main(String[] args) {
		SpringApplication.run(FlightsearchApplication.class, args);
	}

}
