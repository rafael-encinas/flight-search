import './Results.css'

import { FlightCard } from '../FlightCard/FlightCard'

export const Results = ()=>{

    let segments1 = [
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
            "carrierCode": "PR"
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
      ];

    //If one flight has multiple segments, create new component using FlightCard 
    return (
        <div className='resultsContainer'>
            <div className='buttonsContainer'>
                <button>Return to search</button>
                <div>
                    <button>Sort by price</button>
                    <button>Sort by duration</button>
                </div>
            </div>
            <FlightCard segments={segments1} />
        </div>
    )
}