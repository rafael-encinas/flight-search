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
  const [sortByDuration, setSortByDuration] = useState(0);
  const [sortedData, setSortedData] = useState(null)

  const { onGetResultFlights }  = useFlights( setData, setSortedData );


/*
          { shownUi == 0? <SearchContainer onGetResultFlights={onGetResultFlights} /> :
           shownUi == 1? <Results data={response} /> : <FlightDetails data={response.data[0]} />}

*/

function updatePriceSorting(priceSorting:any){
      console.log("Price sorting at function : " +priceSorting)
      if(priceSorting == 0){
        //unsorted
        let clonedData = [...data]
        setSortedData(clonedData);
        console.log(sortedData)
      } else if (priceSorting == 1){
        let clonedData = [...sortedData]
        // PriceAsc
        setSortedData(clonedData.sort(compareByPriceAsc));
        console.log(sortedData)
      } else{
        let clonedData = [...sortedData]
        //PriceDesc
        setSortedData(clonedData.sort(compareByPriceDesc));
        console.log(sortedData)
      }
}

function updateDurationSorting(durationSorting){
  console.log("Duration sorting at function : " +durationSorting)
  if(durationSorting == 0){
    //unsorted
    let clonedData = [...data]
    setSortedData(clonedData);
    console.log(sortedData)
  } else if (durationSorting == 1){
    let clonedData = [...sortedData]
    // DurationAsc
    setSortedData(clonedData.sort(compareByDurationAsc));
    console.log(sortedData)
  } else{
    let clonedData = [...sortedData]
    //DurationDesc
    setSortedData(clonedData.sort(compareByDurationDesc));
    console.log(sortedData)
  }
}

function compareByPriceAsc(a:any, b:any) {
  return a.grandTotal - b.grandTotal;
}

function compareByPriceDesc(a:any, b:any) {
  return b.grandTotal - a.grandTotal;
}
function compareByDurationAsc(a:any, b:any){
  return a.totalTravelTime - b.totalTravelTime;
}
function compareByDurationDesc(a:any, b:any){
  return b.totalTravelTime - a.totalTravelTime;
}



  return (
    <>
          { sortedData==null? <SearchContainer onGetResultFlights={onGetResultFlights} /> 
          : 
          <Results setSortedData={setSortedData} data={sortedData} updatePriceSorting={updatePriceSorting} updateDurationSorting={updateDurationSorting} />}

    </>
  )
}

export default App
