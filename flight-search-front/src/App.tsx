import { useState } from 'react'
import './App.css'

import { SearchContainer } from './components/SearchContainer/SearchContainer'
import { Results } from './components/Results/Results'
import { FlightDetails } from './components/FlightDetails/FlightDetails'

function App() {
  const [count, setCount] = useState(0)
  const [shownUi, setShownUi] = useState(0);
  const [originLocationCode, setOriginLocationCode] = useState("");
  const [destinationLocationCode, setDestinationLocationCode] = useState("");
  const [departureDate, setDepartureDate] = useState("");
  const [adults, setAdults] = useState(1);
  const [nonStop , setNonStop] = useState(false);

  function showSearchContainer(){
    setShownUi(0);
  }

  function showResults(){
    setShownUi(1);
  }

  function showFlightDetails(){
    setShownUi(2);
  }

  let response = {
    "meta": {
      "count": 2,
      "links": {
        "self": "https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=SYD&destinationLocationCode=BKK&departureDate=2024-11-01&returnDate=2024-11-05&adults=1&max=2"
      }
    },
    "data": [
      {
        "type": "flight-offer",
        "id": "1",
        "source": "GDS",
        "instantTicketingRequired": false,
        "nonHomogeneous": false,
        "oneWay": false,
        "isUpsellOffer": false,
        "lastTicketingDate": "2024-08-29",
        "lastTicketingDateTime": "2024-08-29",
        "numberOfBookableSeats": 9,
        "itineraries": [
          {
            "duration": "PT14H10M",
            "segments": [
              {
                "departure": {
                  "iataCode": "SYD",
                  "terminal": "0",
                  "at": "2024-11-01T07:15:00"
                },
                "arrival": {
                  "iataCode": "DPS",
                  "terminal": "0",
                  "at": "2024-11-01T10:45:00"
                },
                "carrierCode": "OD",
                "number": "172",
                "aircraft": {
                  "code": "738"
                },
                "operating": {
                  "carrierCode": "OD"
                },
                "duration": "PT6H30M",
                "id": "1",
                "numberOfStops": 0,
                "blacklistedInEU": false
              },
              {
                "departure": {
                  "iataCode": "DPS",
                  "terminal": "D",
                  "at": "2024-11-01T14:15:00"
                },
                "arrival": {
                  "iataCode": "DMK",
                  "terminal": "0",
                  "at": "2024-11-01T17:25:00"
                },
                "carrierCode": "ID",
                "number": "7637",
                "aircraft": {
                  "code": "738"
                },
                "operating": {
                  "carrierCode": "ID"
                },
                "duration": "PT4H10M",
                "id": "2",
                "numberOfStops": 0,
                "blacklistedInEU": false
              }
            ]
          },
          {
            "duration": "PT12H25M",
            "segments": [
              {
                "departure": {
                  "iataCode": "DMK",
                  "terminal": "1",
                  "at": "2024-11-05T13:50:00"
                },
                "arrival": {
                  "iataCode": "DPS",
                  "terminal": "D",
                  "at": "2024-11-05T18:55:00"
                },
                "carrierCode": "ID",
                "number": "7636",
                "aircraft": {
                  "code": "738"
                },
                "operating": {
                  "carrierCode": "ID"
                },
                "duration": "PT4H5M",
                "id": "5",
                "numberOfStops": 0,
                "blacklistedInEU": false
              },
              {
                "departure": {
                  "iataCode": "DPS",
                  "terminal": "0",
                  "at": "2024-11-05T21:10:00"
                },
                "arrival": {
                  "iataCode": "SYD",
                  "terminal": "0",
                  "at": "2024-11-06T06:15:00"
                },
                "carrierCode": "OD",
                "number": "171",
                "aircraft": {
                  "code": "738"
                },
                "operating": {
                  "carrierCode": "OD"
                },
                "duration": "PT6H5M",
                "id": "6",
                "numberOfStops": 0,
                "blacklistedInEU": false
              }
            ]
          }
        ],
        "price": {
          "currency": "EUR",
          "total": "409.23",
          "base": "275.00",
          "fees": [
            {
              "amount": "0.00",
              "type": "SUPPLIER"
            },
            {
              "amount": "0.00",
              "type": "TICKETING"
            }
          ],
          "grandTotal": "409.23"
        },
        "pricingOptions": {
          "fareType": [
            "PUBLISHED"
          ],
          "includedCheckedBagsOnly": false
        },
        "validatingAirlineCodes": [
          "GP"
        ],
        "travelerPricings": [
          {
            "travelerId": "1",
            "fareOption": "STANDARD",
            "travelerType": "ADULT",
            "price": {
              "currency": "EUR",
              "total": "409.23",
              "base": "275.00"
            },
            "fareDetailsBySegment": [
              {
                "segmentId": "1",
                "cabin": "ECONOMY",
                "fareBasis": "XRTAU",
                "class": "X",
                "includedCheckedBags": {
                  "weight": 0,
                  "weightUnit": "KG"
                }
              },
              {
                "segmentId": "2",
                "cabin": "ECONOMY",
                "fareBasis": "XRTID",
                "class": "X",
                "includedCheckedBags": {
                  "weight": 0,
                  "weightUnit": "KG"
                }
              },
              {
                "segmentId": "5",
                "cabin": "ECONOMY",
                "fareBasis": "XRTID",
                "class": "X",
                "includedCheckedBags": {
                  "weight": 0,
                  "weightUnit": "KG"
                }
              },
              {
                "segmentId": "6",
                "cabin": "ECONOMY",
                "fareBasis": "XRTAU",
                "class": "X",
                "includedCheckedBags": {
                  "weight": 0,
                  "weightUnit": "KG"
                }
              }
            ]
          }
        ]
      },
      {
        "type": "flight-offer",
        "id": "2",
        "source": "GDS",
        "instantTicketingRequired": false,
        "nonHomogeneous": false,
        "oneWay": false,
        "isUpsellOffer": false,
        "lastTicketingDate": "2024-09-15",
        "lastTicketingDateTime": "2024-09-15",
        "numberOfBookableSeats": 5,
        "itineraries": [
          {
            "duration": "PT26H25M",
            "segments": [
              {
                "departure": {
                  "iataCode": "SYD",
                  "terminal": "1",
                  "at": "2024-11-01T12:50:00"
                },
                "arrival": {
                  "iataCode": "XMN",
                  "terminal": "3",
                  "at": "2024-11-01T18:25:00"
                },
                "carrierCode": "MF",
                "number": "802",
                "aircraft": {
                  "code": "789"
                },
                "operating": {
                  "carrierCode": "MF"
                },
                "duration": "PT8H35M",
                "id": "3",
                "numberOfStops": 0,
                "blacklistedInEU": false
              },
              {
                "departure": {
                  "iataCode": "XMN",
                  "terminal": "3",
                  "at": "2024-11-02T08:30:00"
                },
                "arrival": {
                  "iataCode": "BKK",
                  "at": "2024-11-02T11:15:00"
                },
                "carrierCode": "MF",
                "number": "853",
                "aircraft": {
                  "code": "738"
                },
                "operating": {
                  "carrierCode": "MF"
                },
                "duration": "PT3H45M",
                "id": "4",
                "numberOfStops": 0,
                "blacklistedInEU": false
              }
            ]
          },
          {
            "duration": "PT18H35M",
            "segments": [
              {
                "departure": {
                  "iataCode": "BKK",
                  "at": "2024-11-05T12:15:00"
                },
                "arrival": {
                  "iataCode": "XMN",
                  "terminal": "3",
                  "at": "2024-11-05T16:20:00"
                },
                "carrierCode": "MF",
                "number": "854",
                "aircraft": {
                  "code": "738"
                },
                "operating": {
                  "carrierCode": "MF"
                },
                "duration": "PT3H5M",
                "id": "7",
                "numberOfStops": 0,
                "blacklistedInEU": false
              },
              {
                "departure": {
                  "iataCode": "XMN",
                  "terminal": "3",
                  "at": "2024-11-05T22:30:00"
                },
                "arrival": {
                  "iataCode": "SYD",
                  "terminal": "1",
                  "at": "2024-11-06T10:50:00"
                },
                "carrierCode": "MF",
                "number": "801",
                "aircraft": {
                  "code": "789"
                },
                "operating": {
                  "carrierCode": "MF"
                },
                "duration": "PT9H20M",
                "id": "8",
                "numberOfStops": 0,
                "blacklistedInEU": false
              }
            ]
          }
        ],
        "price": {
          "currency": "EUR",
          "total": "434.23",
          "base": "145.00",
          "fees": [
            {
              "amount": "0.00",
              "type": "SUPPLIER"
            },
            {
              "amount": "0.00",
              "type": "TICKETING"
            }
          ],
          "grandTotal": "434.23"
        },
        "pricingOptions": {
          "fareType": [
            "PUBLISHED"
          ],
          "includedCheckedBagsOnly": true
        },
        "validatingAirlineCodes": [
          "MF"
        ],
        "travelerPricings": [
          {
            "travelerId": "1",
            "fareOption": "STANDARD",
            "travelerType": "ADULT",
            "price": {
              "currency": "EUR",
              "total": "434.23",
              "base": "145.00"
            },
            "fareDetailsBySegment": [
              {
                "segmentId": "3",
                "cabin": "ECONOMY",
                "fareBasis": "S3M6AAUS",
                "brandedFare": "YSTANDARD",
                "brandedFareLabel": "ECONOMY STANDARD",
                "class": "S",
                "includedCheckedBags": {
                  "quantity": 1
                },
                "amenities": [
                  {
                    "description": "CHECKED BAG 1PC OF 23KG 158CM",
                    "isChargeable": false,
                    "amenityType": "BAGGAGE",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  },
                  {
                    "description": "REFUNDABLE  TICKET",
                    "isChargeable": true,
                    "amenityType": "BRANDED_FARES",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  },
                  {
                    "description": "CHANGEABLE  TICKET",
                    "isChargeable": true,
                    "amenityType": "BRANDED_FARES",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  }
                ]
              },
              {
                "segmentId": "4",
                "cabin": "ECONOMY",
                "fareBasis": "S3M6AAUS",
                "brandedFare": "YSTANDARD",
                "brandedFareLabel": "ECONOMY STANDARD",
                "class": "S",
                "includedCheckedBags": {
                  "quantity": 1
                },
                "amenities": [
                  {
                    "description": "CHECKED BAG 1PC OF 23KG 158CM",
                    "isChargeable": false,
                    "amenityType": "BAGGAGE",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  },
                  {
                    "description": "REFUNDABLE  TICKET",
                    "isChargeable": true,
                    "amenityType": "BRANDED_FARES",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  },
                  {
                    "description": "CHANGEABLE  TICKET",
                    "isChargeable": true,
                    "amenityType": "BRANDED_FARES",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  }
                ]
              },
              {
                "segmentId": "7",
                "cabin": "ECONOMY",
                "fareBasis": "S3M6AAUS",
                "brandedFare": "YSTANDARD",
                "brandedFareLabel": "ECONOMY STANDARD",
                "class": "S",
                "includedCheckedBags": {
                  "quantity": 1
                },
                "amenities": [
                  {
                    "description": "CHECKED BAG 1PC OF 23KG 158CM",
                    "isChargeable": false,
                    "amenityType": "BAGGAGE",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  },
                  {
                    "description": "REFUNDABLE  TICKET",
                    "isChargeable": true,
                    "amenityType": "BRANDED_FARES",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  },
                  {
                    "description": "CHANGEABLE  TICKET",
                    "isChargeable": true,
                    "amenityType": "BRANDED_FARES",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  }
                ]
              },
              {
                "segmentId": "8",
                "cabin": "ECONOMY",
                "fareBasis": "S3M6AAUS",
                "brandedFare": "YSTANDARD",
                "brandedFareLabel": "ECONOMY STANDARD",
                "class": "S",
                "includedCheckedBags": {
                  "quantity": 1
                },
                "amenities": [
                  {
                    "description": "CHECKED BAG 1PC OF 23KG 158CM",
                    "isChargeable": false,
                    "amenityType": "BAGGAGE",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  },
                  {
                    "description": "REFUNDABLE  TICKET",
                    "isChargeable": true,
                    "amenityType": "BRANDED_FARES",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  },
                  {
                    "description": "CHANGEABLE  TICKET",
                    "isChargeable": true,
                    "amenityType": "BRANDED_FARES",
                    "amenityProvider": {
                      "name": "BrandedFare"
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    ],
    "dictionaries": {
      "locations": {
        "DMK": {
          "cityCode": "BKK",
          "countryCode": "TH"
        },
        "BKK": {
          "cityCode": "BKK",
          "countryCode": "TH"
        },
        "DPS": {
          "cityCode": "DPS",
          "countryCode": "ID"
        },
        "XMN": {
          "cityCode": "XMN",
          "countryCode": "CN"
        },
        "SYD": {
          "cityCode": "SYD",
          "countryCode": "AU"
        }
      },
      "aircraft": {
        "738": "BOEING 737-800",
        "789": "BOEING 787-9"
      },
      "currencies": {
        "EUR": "EURO"
      },
      "carriers": {
        "OD": "BATIK AIR MALAYSIA",
        "MF": "XIAMEN AIRLINES",
        "ID": "BATIK AIR INDONESIA"
      }
    }
  };

  




  return (
    <>
          <button onClick={showSearchContainer}>Show SearchContainer</button>
          <button onClick={showResults}>Show Results</button>
          <button onClick={showFlightDetails}>Show FlightDetails</button>

          { shownUi == 0? <SearchContainer setOriginLocationCode={setOriginLocationCode} /> :
            shownUi == 1? <Results data={response} /> : <FlightDetails data={response.data[0]} />}
          
          {originLocationCode.length>0?`OriginLocationCode set: ${originLocationCode}`: `OriginLocationCode not yet set`}

    </>
  )
}

export default App
