import { useState } from 'react'
import './App.css'

import { SearchContainer } from './components/SearchContainer/SearchContainer'
import { Results } from './components/Results/Results'
import { FlightDetails } from './components/FlightDetails/FlightDetails'
import { useFlights } from './hooks/useFlights.tsx'

function App() {
  const [data, setData] = useState(null);
  const [sortedData, setSortedData] = useState(null)
  const [loading, setLoading] = useState(false);

  const { onGetResultFlights }  = useFlights( setData, setSortedData, setLoading );

function mixedSorting(priceSorting:any, durationSorting:any, lastSorting:any){
  //Unsorted, original order from backend
  let clonedData = [...data];

  if(lastSorting == 0){
    //First sort by duration, then by price
    
    //Duration Sorting
    if(durationSorting == 0){
      //unsorted
    } else if (durationSorting == 1){
      clonedData.sort(compareByDurationAsc)
    } else{
      clonedData.sort(compareByDurationDesc)
    }

    //Price Sorting
    if(priceSorting == 0){
      //unsorted
    } else if (priceSorting == 1){
      clonedData.sort(compareByPriceAsc) 
    } else{
      clonedData.sort(compareByPriceDesc)
    }

  } else{
    //First sort by price, then by duration
        //Price Sorting
        if(priceSorting == 0){
          //unsorted
        } else if (priceSorting == 1){
          clonedData.sort(compareByPriceAsc) 
        } else{
          clonedData.sort(compareByPriceDesc)
        }

        //Duration Sorting
        if(durationSorting == 0){
          //unsorted
        } else if (durationSorting == 1){
          clonedData.sort(compareByDurationAsc)
        } else{
          clonedData.sort(compareByDurationDesc)
        }
  }

  setSortedData(clonedData);

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
          { sortedData==null?
            <>
              <SearchContainer onGetResultFlights={onGetResultFlights} setLoading={setLoading} />
              {loading?<div className='loadingMessage'>Loading...</div>:null}
            </>
            : 
          <Results setSortedData={setSortedData} mixedSorting={mixedSorting} data={sortedData} />
          }

          

    </>
  )
}

export default App
