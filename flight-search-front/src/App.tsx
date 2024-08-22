import { useState } from 'react'
import './App.css'

import { SearchContainer } from './components/SearchContainer/SearchContainer'
import { Results } from './components/Results/Results'
import { FlightDetails } from './components/FlightDetails/FlightDetails'

function App() {
  const [count, setCount] = useState(0)
  const [shownUi, setShownUi] = useState(0);

  function showSearchContainer(){
    setShownUi(0);
  }

  function showResults(){
    setShownUi(1);
  }

  function showFlightDetails(){
    setShownUi(2);
  }

  let mockData = {
    "type": "flight-offer",
    "id": "1",
    "source": "GDS",
    "instantTicketingRequired": false,
    "nonHomogeneous": false,
    "oneWay": false,
    "lastTicketingDate": "2021-11-01",
    "numberOfBookableSeats": 9,
    "itineraries": [
      {
        "duration": "PT14H15M",
        "segments": [
          {
            "departure": {
              "iataCode": "SYD",
              "terminal": "1",
              "at": "2021-11-01T11:35:00"
            },
            "arrival": {
              "iataCode": "MNL",
              "terminal": "2",
              "at": "2021-11-01T16:50:00"
            },
            "carrierCode": "PR",
            "number": "212",
            "aircraft": {
              "code": "333"
            },
            "operating": {
              "carrierCode": "QR"
            },
            "duration": "PT8H15M",
            "id": "1",
            "numberOfStops": 0,
            "blacklistedInEU": false
          },
          {
            "departure": {
              "iataCode": "MNL",
              "terminal": "1",
              "at": "2021-11-01T19:20:00"
            },
            "arrival": {
              "iataCode": "BKK",
              "at": "2021-11-01T21:50:00"
            },
            "carrierCode": "PR",
            "number": "732",
            "aircraft": {
              "code": "320"
            },
            "operating": {
              "carrierCode": "PR"
            },
            "duration": "PT3H30M",
            "id": "2",
            "numberOfStops": 0,
            "blacklistedInEU": false
          }
        ]
      }
    ],
    "price": {
      "currency": "EUR",
      "total": "355.34",
      "base": "255.00",
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
      "grandTotal": "355.34"
    },
    "pricingOptions": {
      "fareType": [
        "PUBLISHED"
      ],
      "includedCheckedBagsOnly": true
    },
    "validatingAirlineCodes": [
      "PR"
    ],
    "travelerPricings": [
      {
        "travelerId": "1",
        "fareOption": "STANDARD",
        "travelerType": "ADULT",
        "price": {
          "currency": "EUR",
          "total": "355.34",
          "base": "255.00"
        },
        "fareDetailsBySegment": [
          {
            "segmentId": "1",
            "cabin": "ECONOMY",
            "fareBasis": "EOBAU",
            "class": "E",
            "includedCheckedBags": {
              "weight": 25,
              "weightUnit": "KG"
            }
          },
          {
            "segmentId": "2",
            "cabin": "ECONOMY",
            "fareBasis": "EOBAU",
            "class": "E",
            "includedCheckedBags": {
              "weight": 25,
              "weightUnit": "KG"
            }
          }
        ]
      }
    ]
  };

  return (
    <>
          <button onClick={showSearchContainer}>Show SearchContainer</button>
          <button onClick={showResults}>Show Results</button>
          <button onClick={showFlightDetails}>Show FlightDetails</button>

          { shownUi == 0? <SearchContainer /> :
            shownUi == 1? <Results /> : <FlightDetails data={mockData} />}
          
          

    </>
  )
}

export default App
