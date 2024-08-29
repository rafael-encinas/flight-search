package com.flightsearchback.flightsearch;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter; 

@Service
public class FlightsearchService {



    @Value("${app.client_id}")
    private String client_id;

    @Value("${app.client_secret}")
    private String client_secret;

    @Value("${app.apiUrl}")
    String apiUrl;

    String access_token= null;
    LocalDateTime tokenCreationTime;

    String mockData2 = "{\"meta\":{\"count\":9,\"links\":{\"self\":\"https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=LAX&destinationLocationCode=GDL&departureDate=2024-09-28&adults=1&nonStop=true&returnDate=2024-10-12\"}},\"data\":[{\"type\":\"flight-offer\",\"id\":\"1\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":5,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T14:35:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T18:45:00\"},\"carrierCode\":\"VB\",\"number\":\"511\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"1\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT2H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T05:45:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T07:10:00\"},\"carrierCode\":\"VB\",\"number\":\"512\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT2H25M\",\"id\":\"6\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"34.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"1\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"6\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"2\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":5,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T23:45:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-29T03:55:00\"},\"carrierCode\":\"VB\",\"number\":\"519\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"2\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT2H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T05:45:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T07:10:00\"},\"carrierCode\":\"VB\",\"number\":\"512\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT2H25M\",\"id\":\"6\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"999.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"2\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"6\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"3\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":5,\"itineraries\":[{\"duration\":\"PT4H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T08:25:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T13:35:00\"},\"carrierCode\":\"VB\",\"number\":\"513\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT4H10M\",\"id\":\"3\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT2H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T05:45:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T07:10:00\"},\"carrierCode\":\"VB\",\"number\":\"512\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT2H25M\",\"id\":\"6\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"4.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"3\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"6\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"4\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":4,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T14:35:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T18:45:00\"},\"carrierCode\":\"VB\",\"number\":\"511\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"1\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T19:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T22:15:00\"},\"carrierCode\":\"VB\",\"number\":\"518\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H25M\",\"id\":\"7\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"324.60\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"1\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"7\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"5\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":4,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T23:45:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-29T03:55:00\"},\"carrierCode\":\"VB\",\"number\":\"519\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"2\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T19:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T22:15:00\"},\"carrierCode\":\"VB\",\"number\":\"518\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H25M\",\"id\":\"7\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"324.40\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"2\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"7\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"6\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":4,\"itineraries\":[{\"duration\":\"PT4H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T08:25:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T13:35:00\"},\"carrierCode\":\"VB\",\"number\":\"513\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT4H10M\",\"id\":\"3\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T19:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T22:15:00\"},\"carrierCode\":\"VB\",\"number\":\"518\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H25M\",\"id\":\"7\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"324.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"3\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"7\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"7\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":9,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T14:35:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T18:45:00\"},\"carrierCode\":\"VB\",\"number\":\"511\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"1\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H30M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T10:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T13:20:00\"},\"carrierCode\":\"VB\",\"number\":\"510\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H30M\",\"id\":\"8\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"345.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"1\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"8\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"BZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"B\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"8\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":9,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T23:45:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-29T03:55:00\"},\"carrierCode\":\"VB\",\"number\":\"519\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"2\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H30M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T10:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T13:20:00\"},\"carrierCode\":\"VB\",\"number\":\"510\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H30M\",\"id\":\"8\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"345.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"2\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"8\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"BZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"B\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"9\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":9,\"itineraries\":[{\"duration\":\"PT4H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T08:25:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T13:35:00\"},\"carrierCode\":\"VB\",\"number\":\"513\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT4H10M\",\"id\":\"3\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H30M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T10:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T13:20:00\"},\"carrierCode\":\"VB\",\"number\":\"510\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H30M\",\"id\":\"8\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"345.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"3\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"8\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"BZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"B\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"10\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-28\",\"lastTicketingDateTime\":\"2024-08-28\",\"numberOfBookableSeats\":7,\"itineraries\":[{\"duration\":\"PT3H17M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"6\",\"at\":\"2024-09-28T10:46:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T15:03:00\"},\"carrierCode\":\"AS\",\"number\":\"1418\",\"aircraft\":{\"code\":\"73J\"},\"operating\":{\"carrierCode\":\"AS\"},\"duration\":\"PT3H17M\",\"id\":\"4\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H20M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T15:55:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"7\",\"at\":\"2024-10-12T18:15:00\"},\"carrierCode\":\"AS\",\"number\":\"1419\",\"aircraft\":{\"code\":\"73J\"},\"operating\":{\"carrierCode\":\"AS\"},\"duration\":\"PT3H20M\",\"id\":\"5\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"436.55\",\"base\":\"277.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"436.55\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"AS\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"436.55\",\"base\":\"277.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"4\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"SH0OASBN\",\"brandedFare\":\"SV\",\"brandedFareLabel\":\"SAVER\",\"class\":\"X\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"STANDARD PIECE MAX 50LB 62LI\",\"isChargeable\":true,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"MEAL 1\",\"isChargeable\":true,\"amenityType\":\"MEAL\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"110V AC POWER\",\"isChargeable\":false,\"amenityType\":\"ENTERTAINMENT\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"USB POWER\",\"isChargeable\":false,\"amenityType\":\"ENTERTAINMENT\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"5\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"SH0OASBN\",\"brandedFare\":\"SV\",\"brandedFareLabel\":\"SAVER\",\"class\":\"X\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"STANDARD PIECE MAX 50LB 62LI\",\"isChargeable\":true,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"MEAL 1\",\"isChargeable\":true,\"amenityType\":\"MEAL\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"110V AC POWER\",\"isChargeable\":false,\"amenityType\":\"ENTERTAINMENT\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"USB POWER\",\"isChargeable\":false,\"amenityType\":\"ENTERTAINMENT\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]}]}";
    String mockData = "{\"meta\":{\"count\":2,\"links\":{ \"self\": \"https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=SYD&destinationLocationCode=BKK&departureDate=2021-11-01&adults=1&max=2\" } }, \"data\":  [  { \"type\": \"flight-offer\", \"id\": \"1\", \"source\": \"GDS\", \"instantTicketingRequired\": false, \"nonHomogeneous\": false, \"oneWay\": false, \"lastTicketingDate\": \"2021-11-01\", \"numberOfBookableSeats\": 9, \"itineraries\":  [  { \"duration\": \"PT14H15M\", \"segments\":  [  { \"departure\":  { \"iataCode\": \"SYD\", \"terminal\": \"1\", \"at\": \"2021-11-01T11:35:00\" }, \"arrival\":  { \"iataCode\": \"MNL\", \"terminal\": \"2\", \"at\": \"2021-11-01T16:50:00\" }, \"carrierCode\": \"PR\", \"number\": \"212\", \"aircraft\":  { \"code\": \"333\" }, \"operating\":  { \"carrierCode\": \"PR\" }, \"duration\": \"PT8H15M\", \"id\": \"1\", \"numberOfStops\": 0, \"blacklistedInEU\": false },  { \"departure\":  { \"iataCode\": \"MNL\", \"terminal\": \"1\", \"at\": \"2021-11-01T19:20:00\" }, \"arrival\":  { \"iataCode\": \"BKK\", \"at\": \"2021-11-01T21:50:00\" }, \"carrierCode\": \"PR\", \"number\": \"732\", \"aircraft\":  { \"code\": \"320\" }, \"operating\":  { \"carrierCode\": \"PR\" }, \"duration\": \"PT3H30M\", \"id\": \"2\", \"numberOfStops\": 0, \"blacklistedInEU\": false } ] } ], \"price\":  { \"currency\": \"EUR\", \"total\": \"355.34\", \"base\": \"255.00\", \"fees\":  [  { \"amount\": \"0.00\", \"type\": \"SUPPLIER\" },  { \"amount\": \"0.00\", \"type\": \"TICKETING\" } ], \"grandTotal\": \"355.34\" }, \"pricingOptions\":  { \"fareType\":  [ \"PUBLISHED\" ], \"includedCheckedBagsOnly\": true }, \"validatingAirlineCodes\":  [ \"PR\" ], \"travelerPricings\":  [  { \"travelerId\": \"1\", \"fareOption\": \"STANDARD\", \"travelerType\": \"ADULT\", \"price\":  { \"currency\": \"EUR\", \"total\": \"355.34\", \"base\": \"255.00\" }, \"fareDetailsBySegment\":  [  { \"segmentId\": \"1\", \"cabin\": \"ECONOMY\", \"fareBasis\": \"EOBAU\", \"class\": \"E\", \"includedCheckedBags\":  { \"weight\": 25, \"weightUnit\": \"KG\" } },  { \"segmentId\": \"2\", \"cabin\": \"ECONOMY\", \"fareBasis\": \"EOBAU\", \"class\": \"E\", \"includedCheckedBags\":  { \"weight\": 25, \"weightUnit\": \"KG\" } } ] } ] },  { \"type\": \"flight-offer\", \"id\": \"2\", \"source\": \"GDS\", \"instantTicketingRequired\": false, \"nonHomogeneous\": false, \"oneWay\": false, \"lastTicketingDate\": \"2021-11-01\", \"numberOfBookableSeats\": 9, \"itineraries\":  [  { \"duration\": \"PT16H35M\", \"segments\":  [  { \"departure\":  { \"iataCode\": \"SYD\", \"terminal\": \"1\", \"at\": \"2021-11-01T11:35:00\" }, \"arrival\":  { \"iataCode\": \"MNL\", \"terminal\": \"2\", \"at\": \"2021-11-01T16:50:00\" }, \"carrierCode\": \"PR\", \"number\": \"212\", \"aircraft\":  { \"code\": \"333\" }, \"operating\":  { \"carrierCode\": \"PR\" }, \"duration\": \"PT8H15M\", \"id\": \"3\", \"numberOfStops\": 0, \"blacklistedInEU\": false },  { \"departure\":  { \"iataCode\": \"MNL\", \"terminal\": \"1\", \"at\": \"2021-11-01T21:40:00\" }, \"arrival\":  { \"iataCode\": \"BKK\", \"at\": \"2021-11-02T00:10:00\" }, \"carrierCode\": \"PR\", \"number\": \"740\", \"aircraft\":  { \"code\": \"321\" }, \"operating\":  { \"carrierCode\": \"PR\" }, \"duration\": \"PT3H30M\", \"id\": \"4\", \"numberOfStops\": 0, \"blacklistedInEU\": false } ] } ], \"price\":  { \"currency\": \"EUR\", \"total\": \"355.34\", \"base\": \"255.00\", \"fees\":  [  { \"amount\": \"0.00\", \"type\": \"SUPPLIER\" },  { \"amount\": \"0.00\", \"type\": \"TICKETING\" } ], \"grandTotal\": \"355.34\" }, \"pricingOptions\":  { \"fareType\":  [ \"PUBLISHED\" ], \"includedCheckedBagsOnly\": true }, \"validatingAirlineCodes\":  [ \"PR\" ], \"travelerPricings\":  [  { \"travelerId\": \"1\", \"fareOption\": \"STANDARD\", \"travelerType\": \"ADULT\", \"price\":  { \"currency\": \"EUR\", \"total\": \"355.34\", \"base\": \"255.00\" }, \"fareDetailsBySegment\":  [  { \"segmentId\": \"3\", \"cabin\": \"ECONOMY\", \"fareBasis\": \"EOBAU\", \"class\": \"E\", \"includedCheckedBags\":  { \"weight\": 25, \"weightUnit\": \"KG\" } },  { \"segmentId\": \"4\", \"cabin\": \"ECONOMY\", \"fareBasis\": \"EOBAU\", \"class\": \"E\", \"includedCheckedBags\":  { \"weight\": 25, \"weightUnit\": \"KG\" } } ] } ] } ], \"dictionaries\":  { \"locations\":  { \"BKK\":  { \"cityCode\": \"BKK\", \"countryCode\": \"TH\" }, \"MNL\":  { \"cityCode\": \"MNL\", \"countryCode\": \"PH\" }, \"SYD\":  { \"cityCode\": \"SYD\", \"countryCode\": \"AU\" } }, \"aircraft\":  { \"320\": \"AIRBUS A320\", \"321\": \"AIRBUS A321\", \"333\": \"AIRBUS A330-300\" }, \"currencies\":  { \"EUR\": \"EURO\" }, \"carriers\":  { \"PR\": \"PHILIPPINE AIRLINES\" } } }";
    
    public String authenticate(){
        String url = apiUrl + "/v1/security/oauth2/token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>( "grant_type=client_credentials&client_id="+ client_id +"&client_secret="+client_secret, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if(response.getStatusCode()== HttpStatus.OK){
            tokenCreationTime = LocalDateTime.now();
           return response.getBody().get("access_token").toString();
        }
/* 
        Tokenresponse tokenResponse = restTemplate.postForObject(url, request, Tokenresponse.class);
        System.out.println(response.getAccess_token());
        if(!response.getAccess_token().equals(null)){
            access_token = response.getAccess_token();
            System.out.println("New access token generated:");
            System.out.println(access_token);
        }
        //System.out.println(response.access_token);
        */
        throw new RuntimeException("API authentication failed");
    }

    public List<Airport> findAirportsByKeyword(String keyword) throws JsonMappingException, JsonProcessingException{

        if(access_token == null){
            access_token= authenticate();
        }
        LocalDateTime requestTime = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(tokenCreationTime, requestTime);
        if(minutes>1790){
            access_token= authenticate();
        }

        
        List<Map> list = null;
        List<Airport> listAirports = new ArrayList<Airport>();
 
        String url = apiUrl + "/v1/reference-data/locations?subType=AIRPORT&keyword="+keyword;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);

        System.out.println("Request url:");
        System.out.println(url);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        list = (List<Map>) response.getBody().get("data");

        for (Map item : list) { // we iterate for each one of the items of the list transforming it
            System.out.println(item.get("address"));

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(item.get("address"));

            ObjectMapper objectMapper = new ObjectMapper();
            AirportAddress address = objectMapper.readValue(json, AirportAddress.class);
            
            Airport airport = new Airport();

            airport.setType(item.get("type").toString());
            airport.setName(item.get("name").toString());
            airport.setIataCode(item.get("iataCode").toString());
            airport.setAddress(address);

            listAirports.add(airport);
        }

        return listAirports;

    }

    public List<TripResult> findFlights(Map<String,String> allParams) throws JsonMappingException, JsonProcessingException{

        if(access_token == null){
            access_token= authenticate();
        }
        LocalDateTime requestTime = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(tokenCreationTime, requestTime);
        if(minutes>1790){
            access_token= authenticate();
        }

        List<Map> list = null;
        Map dictionaries = null;
        List<TripResult> tripResults = new ArrayList<TripResult>();
 
        String url = apiUrl + "/v2/shopping/flight-offers?originLocationCode="+ allParams.get("originLocationCode")
                            +"&destinationLocationCode="+ allParams.get("destinationLocationCode")
                            +"&departureDate="+ allParams.get("departureDate")
                            +"&adults="+ allParams.get("adults")
                            +"&nonStop=" + allParams.get("nonStop")
                            +"&currencyCode" + allParams.get("currencyCode")
                            +"&max=25";
        if(allParams.containsKey("returnDate")){
            url = url + "&returnDate=" + allParams.get("returnDate");
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);

        System.out.println("Request url:");
        System.out.println(url);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        //Amadeus API Request

        /* 
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if(response.getStatusCode() == HttpStatus.OK){
            list = (List<Map>) response.getBody().get("data");

        } else {
            //In case Amadeus API is down
            throw new RuntimeException("Error: Amadeus API is down.");
            //ObjectMapper objectMapperMock = new ObjectMapper();
            //Map mockResponse = objectMapperMock.readValue(mockData, Map.class);
            //list = (List<Map>) mockResponse.get("data");
        }
        */
        //list = (List<Map>) response.getBody().get("data");

            ObjectMapper objectMapperMock = new ObjectMapper();
            Map mockResponse = objectMapperMock.readValue(mockData, Map.class);
            list = (List<Map>) mockResponse.get("data");
            dictionaries = (Map) mockResponse.get("dictionaries");
            System.out.println(dictionaries);
            System.out.println(dictionaries.get("aircraft"));

        for (Map item : list) { // we iterate for each one of the items of the list transforming it
            //We get access to all the superficial data inside each array item, like type, id, source, numberOfBookableSeats, etc
            TripResult tripResult = new TripResult();
            tripResult.setId(item.get("id").toString());
            //System.out.println("Each item");
            
            //System.out.println(item.get("itineraries"));
            
            
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonItineraries = ow.writeValueAsString(item.get("itineraries"));
            Itinerary[] itineraries = objectMapper.readValue(jsonItineraries, Itinerary[].class);
            for(Itinerary itinerary : itineraries){
                for(Segment segment : itinerary.getSegments()){
                    segment.setCarrierDescription(dictionaries.get("carriers").toString());
                    segment.getAircraft().setDescription(dictionaries.get("aircraft").toString());
                    segment.getOperating().setCarrierDescription(dictionaries.get("carriers").toString());
                }
            }
            tripResult.setItineraries(itineraries);

            String jsonPrices = ow.writeValueAsString(item.get("price"));
            Price prices = objectMapper.readValue(jsonPrices, Price.class);
            tripResult.setGrandTotal(prices.getGrandTotal());
            //System.out.println(prices.getGrandTotal());
            tripResult.setPrice(prices);

            //substring index 2 - index (h)
            String durationString = itineraries[0].getDuration();
            //System.out.println("Departure duration");
            //System.out.println(durationString);
            int hIndex = durationString.indexOf("H");
            int mIndex = durationString.indexOf("M");
            String hoursString = durationString.substring(2, hIndex);
            String minutesString = durationString.substring(hIndex+1, mIndex);

            int totalTravelTime = (Integer.parseInt(hoursString)*60) + Integer.parseInt(minutesString);
            //System.out.println("TotalTravelTime in minutes");
            //System.out.println(totalTravelTime);
            tripResult.setTotalTravelTime(totalTravelTime);


            String jsonTravelerPricing = ow.writeValueAsString(item.get("travelerPricings"));
            TravelerPricing[] travelersPricings = objectMapper.readValue(jsonTravelerPricing, TravelerPricing[].class);
            tripResult.setTravelerPricings(travelersPricings);

            tripResults.add(tripResult);

            System.out.println("////////////////");
        }

        tripResults = sortByDuration(tripResults);

        return tripResults;
        
    }

    private List<TripResult> sortByPrice(List<TripResult> list){
        list.sort(
            //(a,b) -> String.valueOf(a.getGrandTotal()).compareTo(String.valueOf(b.getGrandTotal()))
            (a,b) -> Float.compare(Float.parseFloat(a.getGrandTotal()), Float.parseFloat(b.getGrandTotal()))
        );
        return list;
    }
    private List<TripResult> sortByDuration(List<TripResult> list){
        list.sort(
            //(a,b) -> String.valueOf(a.getGrandTotal()).compareTo(String.valueOf(b.getGrandTotal()))
            (a,b) -> a.getTotalTravelTime() - b.getTotalTravelTime()
        );
        return list;
    }
}
