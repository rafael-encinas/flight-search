package com.flightsearchback.flightsearch;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.*;

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

    Map<String, String> iataDictionaryMap = new HashMap<>();

    String mockData2 = "{\"meta\":{\"count\":9,\"links\":{\"self\":\"https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=LAX&destinationLocationCode=GDL&departureDate=2024-09-28&adults=1&nonStop=true&returnDate=2024-10-12\"}},\"data\":[{\"type\":\"flight-offer\",\"id\":\"1\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":5,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T14:35:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T18:45:00\"},\"carrierCode\":\"VB\",\"number\":\"511\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"1\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT2H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T05:45:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T07:10:00\"},\"carrierCode\":\"VB\",\"number\":\"512\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT2H25M\",\"id\":\"6\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"34.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"1\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"6\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"2\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":5,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T23:45:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-29T03:55:00\"},\"carrierCode\":\"VB\",\"number\":\"519\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"2\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT2H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T05:45:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T07:10:00\"},\"carrierCode\":\"VB\",\"number\":\"512\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT2H25M\",\"id\":\"6\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"999.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"2\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"6\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"3\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":5,\"itineraries\":[{\"duration\":\"PT4H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T08:25:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T13:35:00\"},\"carrierCode\":\"VB\",\"number\":\"513\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT4H10M\",\"id\":\"3\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT2H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T05:45:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T07:10:00\"},\"carrierCode\":\"VB\",\"number\":\"512\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT2H25M\",\"id\":\"6\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"4.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"3\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"6\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"4\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":4,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T14:35:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T18:45:00\"},\"carrierCode\":\"VB\",\"number\":\"511\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"1\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T19:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T22:15:00\"},\"carrierCode\":\"VB\",\"number\":\"518\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H25M\",\"id\":\"7\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"324.60\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"1\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"7\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"5\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":4,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T23:45:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-29T03:55:00\"},\"carrierCode\":\"VB\",\"number\":\"519\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"2\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T19:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T22:15:00\"},\"carrierCode\":\"VB\",\"number\":\"518\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H25M\",\"id\":\"7\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"324.40\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"2\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"7\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"6\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":4,\"itineraries\":[{\"duration\":\"PT4H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T08:25:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T13:35:00\"},\"carrierCode\":\"VB\",\"number\":\"513\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT4H10M\",\"id\":\"3\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H25M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T19:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T22:15:00\"},\"carrierCode\":\"VB\",\"number\":\"518\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H25M\",\"id\":\"7\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"324.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"324.50\",\"base\":\"169.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"3\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"7\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"ZZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"Z\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"7\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":9,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T14:35:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T18:45:00\"},\"carrierCode\":\"VB\",\"number\":\"511\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"1\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H30M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T10:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T13:20:00\"},\"carrierCode\":\"VB\",\"number\":\"510\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H30M\",\"id\":\"8\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"345.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"1\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"8\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"BZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"B\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"8\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":9,\"itineraries\":[{\"duration\":\"PT3H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T23:45:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-29T03:55:00\"},\"carrierCode\":\"VB\",\"number\":\"519\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H10M\",\"id\":\"2\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H30M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T10:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T13:20:00\"},\"carrierCode\":\"VB\",\"number\":\"510\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H30M\",\"id\":\"8\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"345.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"2\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"8\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"BZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"B\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"9\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-27\",\"lastTicketingDateTime\":\"2024-08-27\",\"numberOfBookableSeats\":9,\"itineraries\":[{\"duration\":\"PT4H10M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"1\",\"at\":\"2024-09-28T08:25:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T13:35:00\"},\"carrierCode\":\"VB\",\"number\":\"513\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT4H10M\",\"id\":\"3\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H30M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T10:50:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"B\",\"at\":\"2024-10-12T13:20:00\"},\"carrierCode\":\"VB\",\"number\":\"510\",\"aircraft\":{\"code\":\"320\"},\"operating\":{\"carrierCode\":\"VB\"},\"duration\":\"PT3H30M\",\"id\":\"8\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"345.50\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"VB\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"345.50\",\"base\":\"190.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"3\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"HZGO00I1\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"H\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"8\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"BZGO00I\",\"brandedFare\":\"ZERO\",\"brandedFareLabel\":\"VIVA ZERO\",\"class\":\"B\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"LAPTOP OR HANDBAG UP TO 85LCM\",\"isChargeable\":false,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]},{\"type\":\"flight-offer\",\"id\":\"10\",\"source\":\"GDS\",\"instantTicketingRequired\":false,\"nonHomogeneous\":false,\"oneWay\":false,\"isUpsellOffer\":false,\"lastTicketingDate\":\"2024-08-28\",\"lastTicketingDateTime\":\"2024-08-28\",\"numberOfBookableSeats\":7,\"itineraries\":[{\"duration\":\"PT3H17M\",\"segments\":[{\"departure\":{\"iataCode\":\"LAX\",\"terminal\":\"6\",\"at\":\"2024-09-28T10:46:00\"},\"arrival\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-09-28T15:03:00\"},\"carrierCode\":\"AS\",\"number\":\"1418\",\"aircraft\":{\"code\":\"73J\"},\"operating\":{\"carrierCode\":\"AS\"},\"duration\":\"PT3H17M\",\"id\":\"4\",\"numberOfStops\":0,\"blacklistedInEU\":false}]},{\"duration\":\"PT3H20M\",\"segments\":[{\"departure\":{\"iataCode\":\"GDL\",\"terminal\":\"1\",\"at\":\"2024-10-12T15:55:00\"},\"arrival\":{\"iataCode\":\"LAX\",\"terminal\":\"7\",\"at\":\"2024-10-12T18:15:00\"},\"carrierCode\":\"AS\",\"number\":\"1419\",\"aircraft\":{\"code\":\"73J\"},\"operating\":{\"carrierCode\":\"AS\"},\"duration\":\"PT3H20M\",\"id\":\"5\",\"numberOfStops\":0,\"blacklistedInEU\":false}]}],\"price\":{\"currency\":\"EUR\",\"total\":\"436.55\",\"base\":\"277.00\",\"fees\":[{\"amount\":\"0.00\",\"type\":\"SUPPLIER\"},{\"amount\":\"0.00\",\"type\":\"TICKETING\"}],\"grandTotal\":\"436.55\"},\"pricingOptions\":{\"fareType\":[\"PUBLISHED\"],\"includedCheckedBagsOnly\":false},\"validatingAirlineCodes\":[\"AS\"],\"travelerPricings\":[{\"travelerId\":\"1\",\"fareOption\":\"STANDARD\",\"travelerType\":\"ADULT\",\"price\":{\"currency\":\"EUR\",\"total\":\"436.55\",\"base\":\"277.00\"},\"fareDetailsBySegment\":[{\"segmentId\":\"4\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"SH0OASBN\",\"brandedFare\":\"SV\",\"brandedFareLabel\":\"SAVER\",\"class\":\"X\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"STANDARD PIECE MAX 50LB 62LI\",\"isChargeable\":true,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"MEAL 1\",\"isChargeable\":true,\"amenityType\":\"MEAL\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"110V AC POWER\",\"isChargeable\":false,\"amenityType\":\"ENTERTAINMENT\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"USB POWER\",\"isChargeable\":false,\"amenityType\":\"ENTERTAINMENT\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]},{\"segmentId\":\"5\",\"cabin\":\"ECONOMY\",\"fareBasis\":\"SH0OASBN\",\"brandedFare\":\"SV\",\"brandedFareLabel\":\"SAVER\",\"class\":\"X\",\"includedCheckedBags\":{\"quantity\":0},\"amenities\":[{\"description\":\"STANDARD PIECE MAX 50LB 62LI\",\"isChargeable\":true,\"amenityType\":\"BAGGAGE\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"MEAL 1\",\"isChargeable\":true,\"amenityType\":\"MEAL\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"110V AC POWER\",\"isChargeable\":false,\"amenityType\":\"ENTERTAINMENT\",\"amenityProvider\":{\"name\":\"BrandedFare\"}},{\"description\":\"USB POWER\",\"isChargeable\":false,\"amenityType\":\"ENTERTAINMENT\",\"amenityProvider\":{\"name\":\"BrandedFare\"}}]}]}]}], \"dictionaries\":  { \"locations\":  { \"BKK\":  { \"cityCode\": \"BKK\", \"countryCode\": \"TH\" }, \"MNL\":  { \"cityCode\": \"MNL\", \"countryCode\": \"PH\" }, \"SYD\":  { \"cityCode\": \"SYD\", \"countryCode\": \"AU\" } }, \"aircraft\":  { \"320\": \"AIRBUS A320\", \"321\": \"AIRBUS A321\", \"333\": \"AIRBUS A330-300\" }, \"currencies\":  { \"EUR\": \"EURO\" }, \"carriers\":  { \"PR\": \"PHILIPPINE AIRLINES\" } }}";
    String mockData = "{\"meta\":{\"count\":2,\"links\":{ \"self\": \"https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=SYD&destinationLocationCode=BKK&departureDate=2021-11-01&adults=1&max=2\" } }, \"data\":  [  { \"type\": \"flight-offer\", \"id\": \"1\", \"source\": \"GDS\", \"instantTicketingRequired\": false, \"nonHomogeneous\": false, \"oneWay\": false, \"lastTicketingDate\": \"2021-11-01\", \"numberOfBookableSeats\": 9, \"itineraries\":  [  { \"duration\": \"PT14H15M\", \"segments\":  [  { \"departure\":  { \"iataCode\": \"SYD\", \"terminal\": \"1\", \"at\": \"2021-11-01T11:35:00\" }, \"arrival\":  { \"iataCode\": \"MNL\", \"terminal\": \"2\", \"at\": \"2021-11-01T16:50:00\" }, \"carrierCode\": \"PR\", \"number\": \"212\", \"aircraft\":  { \"code\": \"333\" }, \"operating\":  { \"carrierCode\": \"PR\" }, \"duration\": \"PT8H15M\", \"id\": \"1\", \"numberOfStops\": 0, \"blacklistedInEU\": false },  { \"departure\":  { \"iataCode\": \"MNL\", \"terminal\": \"1\", \"at\": \"2021-11-01T19:20:00\" }, \"arrival\":  { \"iataCode\": \"BKK\", \"at\": \"2021-11-01T21:50:00\" }, \"carrierCode\": \"PR\", \"number\": \"732\", \"aircraft\":  { \"code\": \"320\" }, \"operating\":  { \"carrierCode\": \"PR\" }, \"duration\": \"PT3H30M\", \"id\": \"2\", \"numberOfStops\": 0, \"blacklistedInEU\": false } ] } ], \"price\":  { \"currency\": \"EUR\", \"total\": \"355.34\", \"base\": \"255.00\", \"fees\":  [  { \"amount\": \"0.00\", \"type\": \"SUPPLIER\" },  { \"amount\": \"0.00\", \"type\": \"TICKETING\" } ], \"grandTotal\": \"355.34\" }, \"pricingOptions\":  { \"fareType\":  [ \"PUBLISHED\" ], \"includedCheckedBagsOnly\": true }, \"validatingAirlineCodes\":  [ \"PR\" ], \"travelerPricings\":  [  { \"travelerId\": \"1\", \"fareOption\": \"STANDARD\", \"travelerType\": \"ADULT\", \"price\":  { \"currency\": \"EUR\", \"total\": \"400.34\", \"base\": \"255.00\" }, \"fareDetailsBySegment\":  [  { \"segmentId\": \"1\", \"cabin\": \"ECONOMY\", \"fareBasis\": \"EOBAU\", \"class\": \"E\", \"includedCheckedBags\":  { \"weight\": 25, \"weightUnit\": \"KG\" } },  { \"segmentId\": \"2\", \"cabin\": \"ECONOMY\", \"fareBasis\": \"EOBAU\", \"class\": \"E\", \"includedCheckedBags\":  { \"weight\": 25, \"weightUnit\": \"KG\" } } ] } ] },  { \"type\": \"flight-offer\", \"id\": \"2\", \"source\": \"GDS\", \"instantTicketingRequired\": false, \"nonHomogeneous\": false, \"oneWay\": false, \"lastTicketingDate\": \"2021-11-01\", \"numberOfBookableSeats\": 9, \"itineraries\":  [  { \"duration\": \"PT16H35M\", \"segments\":  [  { \"departure\":  { \"iataCode\": \"SYD\", \"terminal\": \"1\", \"at\": \"2021-11-01T11:35:00\" }, \"arrival\":  { \"iataCode\": \"MNL\", \"terminal\": \"2\", \"at\": \"2021-11-01T16:50:00\" }, \"carrierCode\": \"PR\", \"number\": \"212\", \"aircraft\":  { \"code\": \"333\" }, \"operating\":  { \"carrierCode\": \"PR\" }, \"duration\": \"PT8H15M\", \"id\": \"3\", \"numberOfStops\": 0, \"blacklistedInEU\": false },  { \"departure\":  { \"iataCode\": \"MNL\", \"terminal\": \"1\", \"at\": \"2021-11-01T21:40:00\" }, \"arrival\":  { \"iataCode\": \"BKK\", \"at\": \"2021-11-02T00:10:00\" }, \"carrierCode\": \"PR\", \"number\": \"740\", \"aircraft\":  { \"code\": \"321\" }, \"operating\":  { \"carrierCode\": \"PR\" }, \"duration\": \"PT3H30M\", \"id\": \"4\", \"numberOfStops\": 0, \"blacklistedInEU\": false } ] } ], \"price\":  { \"currency\": \"EUR\", \"total\": \"400.34\", \"base\": \"255.00\", \"fees\":  [  { \"amount\": \"0.00\", \"type\": \"SUPPLIER\" },  { \"amount\": \"0.00\", \"type\": \"TICKETING\" } ], \"grandTotal\": \"355.34\" }, \"pricingOptions\":  { \"fareType\":  [ \"PUBLISHED\" ], \"includedCheckedBagsOnly\": true }, \"validatingAirlineCodes\":  [ \"PR\" ], \"travelerPricings\":  [  { \"travelerId\": \"1\", \"fareOption\": \"STANDARD\", \"travelerType\": \"ADULT\", \"price\":  { \"currency\": \"EUR\", \"total\": \"355.34\", \"base\": \"255.00\" }, \"fareDetailsBySegment\":  [  { \"segmentId\": \"3\", \"cabin\": \"ECONOMY\", \"fareBasis\": \"EOBAU\", \"class\": \"E\", \"includedCheckedBags\":  { \"weight\": 25, \"weightUnit\": \"KG\" } },  { \"segmentId\": \"4\", \"cabin\": \"ECONOMY\", \"fareBasis\": \"EOBAU\", \"class\": \"E\", \"includedCheckedBags\":  { \"weight\": 25, \"weightUnit\": \"KG\" } } ] } ] } ], \"dictionaries\":  { \"locations\":  { \"BKK\":  { \"cityCode\": \"BKK\", \"countryCode\": \"TH\" }, \"MNL\":  { \"cityCode\": \"MNL\", \"countryCode\": \"PH\" }, \"SYD\":  { \"cityCode\": \"SYD\", \"countryCode\": \"AU\" } }, \"aircraft\":  { \"320\": \"AIRBUS A320\", \"321\": \"AIRBUS A321\", \"333\": \"AIRBUS A330-300\" }, \"currencies\":  { \"EUR\": \"EURO\" }, \"carriers\":  { \"PR\": \"PHILIPPINE AIRLINES\" } } }";
    String mockIata = "{ \"meta\":  { \"count\": 2, \"links\":  { \"self\": \"https://test.api.amadeus.com/v1/reference-data/locations?subType=CITY,AIRPORT&keyword=MUC&countryCode=DE\" } }, \"data\": [  { \"type\": \"location\", \"subType\": \"CITY\", \"name\": \"MUNICH INTERNATIONAL\", \"detailedName\": \"MUNICH/DE:MUNICH INTERNATIONAL\", \"id\": \"CMUC\", \"self\":  { \"href\": \"https://test.api.amadeus.com/v1/reference-data/locations/CMUC\", \"methods\": [ \"GET\" ] }, \"timeZoneOffset\": \"+02:00\", \"iataCode\": \"MUC\", \"geoCode\":  { \"latitude\": 48.35378, \"longitude\": 11.78609 }, \"address\":  { \"cityName\": \"MUNICH\", \"cityCode\": \"MUC\", \"countryName\": \"GERMANY\", \"countryCode\": \"DE\", \"regionCode\": \"EUROP\" }, \"analytics\":  { \"travelers\":  { \"score\": 27 } } },  { \"type\": \"location\", \"subType\": \"AIRPORT\", \"name\": \"MUNICH INTERNATIONAL\", \"detailedName\": \"MUNICH/DE:MUNICH INTERNATIONAL\", \"id\": \"AMUC\", \"self\":  { \"href\": \"https://test.api.amadeus.com/v1/reference-data/locations/AMUC\", \"methods\": [ \"GET\" ] }, \"timeZoneOffset\": \"+02:00\", \"iataCode\": \"MUC\", \"geoCode\":  { \"latitude\": 48.35378, \"longitude\": 11.78609 }, \"address\":  { \"cityName\": \"MUNICH\", \"cityCode\": \"MUC\", \"countryName\": \"GERMANY\", \"countryCode\": \"DE\", \"regionCode\": \"EUROP\" }, \"analytics\":  { \"travelers\":  { \"score\": 27 } } } ] }";
    
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

        throw new RuntimeException("API authentication failed");
    }

    public List<Airport> findAirportsByKeyword(String keyword) throws JsonMappingException, JsonProcessingException{

        if(access_token == null){
            access_token= authenticate();
        }
        LocalDateTime requestTime = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(tokenCreationTime, requestTime);
        if(minutes>28){
            access_token= authenticate();
        }

        
        List<Map> list = null;
        List<Airport> listAirports = new ArrayList<Airport>();
 
        String url = apiUrl + "/v1/reference-data/locations?subType=AIRPORT&keyword="+keyword;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try{
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            list = (List<Map>) response.getBody().get("data");
        }
        catch(Exception e){
            ObjectMapper objectMapperMock = new ObjectMapper();
            Map mockResponse = objectMapperMock.readValue(mockIata, Map.class);
            list = (List<Map>) mockResponse.get("data");
            System.out.println("Couldn't access Amadeus API Iata code search");
        }

        for (Map item : list) { // we iterate for each one of the items of the list transforming it
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

    public String findCityNameFromIata(String iataCode) throws JsonMappingException, JsonProcessingException{

        String cityName = "N/A";
        if(iataDictionaryMap.containsKey(iataCode)){
            return iataDictionaryMap.get(iataCode);
        }

        List<Airport> airportResult =  findAirportsByKeyword(iataCode);
        if(airportResult.size()>0){
            cityName = airportResult.get(0).getAddress().getCityName().toString();
            iataDictionaryMap.put(iataCode, cityName);
        }

        return cityName;
    }
    
    public List<TripResult> findFlights(Map<String,String> allParams) throws JsonMappingException, JsonProcessingException{

        if(access_token == null){
            access_token= authenticate();
        }
        LocalDateTime requestTime = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(tokenCreationTime, requestTime);
        if(minutes>28){
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
                            +"&currencyCode=" + allParams.get("currencyCode")
                            +"&max=10";
        if(allParams.containsKey("returnDate")){
            url = url + "&returnDate=" + allParams.get("returnDate");
        }
        System.out.println("Request URL");
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ObjectMapper objectMapperMock = new ObjectMapper();
        Map mockResponse = objectMapperMock.readValue(mockData, Map.class);

        try{
            //Amadeus API Request
            System.out.println("Trying to access amadeus flight search api");
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            System.out.println(response.getStatusCode());
            list = (List<Map>) response.getBody().get("data");
            if(list.size()<=0){
                System.out.println("No results for the query");
                return tripResults;
            }
            //System.out.println(list);
            dictionaries = (Map) response.getBody().get("dictionaries");
            //System.out.println(dictionaries);
        } catch(Exception e){
            //If API is unavailable, use mock data
            list = (List<Map>) mockResponse.get("data");
            dictionaries = (Map) mockResponse.get("dictionaries");
            System.out.println("Couldn't access Amadeus API Flight search");
        }


            Map<String, String> aircraft = (Map<String, String>) dictionaries.get("aircraft");
            Map<String, String> carriers = (Map<String, String>) dictionaries.get("carriers");

        for (Map item : list) { // we iterate for each one of the items of the list transforming it
            //We get access to all the superficial data inside each array item, like type, id, source, numberOfBookableSeats, etc
            TripResult tripResult = new TripResult();
            tripResult.setId(item.get("id").toString());

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonItineraries = ow.writeValueAsString(item.get("itineraries"));
            Itinerary[] itineraries = objectMapper.readValue(jsonItineraries, Itinerary[].class);
            for(Itinerary itinerary : itineraries){
                for(Segment segment : itinerary.getSegments()){
                    
                    //Commented because mockData dictionary doesn't match the request data
                    //Uncomment when API is working and dictionary has real data

                    segment.setCarrierDescription(carriers.get(segment.getCarrierCode()));
                    segment.getAircraft().setDescription(aircraft.get(segment.getAircraft().getCode()));
                    //segment.getOperating().setCarrierDescription(carriers.get(segment.getOperating().getCarrierCode()));
                    if(segment.getOperating()!=null){
                        segment.getOperating().setCarrierDescription(carriers.get(segment.getOperating().getCarrierCode()));
                    }
                    
                    //Should be commented when API is working, to see above to use real dictionary data
                    //segment.setCarrierDescription("Testing without dictionary");
                    //segment.getAircraft().setDescription("Testing without dictionary2");
                    //if(segment.getOperating()!=null){
                    //    segment.getOperating().setCarrierDescription("Testing without dictionary3");
                    //}

                    String departureCity = findCityNameFromIata(segment.getDeparture().getIataCode());
                    segment.getDeparture().setCityName(departureCity);

                    String arrivalCity = findCityNameFromIata(segment.getArrival().getIataCode());
                    segment.getArrival().setCityName(arrivalCity);


                }
            }
            tripResult.setItineraries(itineraries);

            String jsonPrices = ow.writeValueAsString(item.get("price"));
            Price prices = objectMapper.readValue(jsonPrices, Price.class);
            tripResult.setGrandTotal(prices.getGrandTotal());
            tripResult.setPrice(prices);

            //substring (index 2, - index letter (h))
            String durationString = itineraries[0].getDuration();
            String hoursString = "0";
            int hIndex=0;
            if(durationString.contains("H")){
                hIndex = durationString.indexOf("H");
                hoursString = durationString.substring(2, hIndex);
            }
            
            String minutesString= "0";
            if(durationString.contains("M")){
                int mIndex = durationString.indexOf("M");
                minutesString = durationString.substring(hIndex+1, mIndex);
            }
                
            

            int totalTravelTime = (Integer.parseInt(hoursString)*60) + Integer.parseInt(minutesString);
            tripResult.setTotalTravelTime(totalTravelTime);


            String jsonTravelerPricing = ow.writeValueAsString(item.get("travelerPricings"));
            TravelerPricing[] travelersPricings = objectMapper.readValue(jsonTravelerPricing, TravelerPricing[].class);
            tripResult.setTravelerPricings(travelersPricings);

            tripResults.add(tripResult);
        }

        return tripResults;
        
    }
}
