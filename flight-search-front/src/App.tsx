import { useState } from 'react'
import './App.css'

import { SearchContainer } from './components/SearchContainer/SearchContainer'
import { Results } from './components/Results/Results'
import { FlightDetails } from './components/FlightDetails/FlightDetails'
import { useFlights } from './hooks/useFlights.tsx'

function App() {
  const [destinationLocationCode, setDestinationLocationCode] = useState("");
  const [departureDate, setDepartureDate] = useState("");
  const [adults, setAdults] = useState(1);
  const [nonStop , setNonStop] = useState(false);
  const [data, setData] = useState(null);

  const { onGetResultFlights }  = useFlights( setData );


/*
          { shownUi == 0? <SearchContainer onGetResultFlights={onGetResultFlights} /> :
           shownUi == 1? <Results data={response} /> : <FlightDetails data={response.data[0]} />}

*/



  return (
    <>
          { data==null? <SearchContainer onGetResultFlights={onGetResultFlights} /> : <Results setData={setData} data={data} />}
    
          <div>Data is {data==null?"Empty :(":"Set! Nice!"}</div>

    </>
  )
}

export default App
