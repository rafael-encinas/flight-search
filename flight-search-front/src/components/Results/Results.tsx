import { useState } from 'react'
import './Results.css'

import { FlightCard } from '../FlightCard/FlightCard';

type ResultsProps = {
  data: any,
  setSortedData: any,
  mixedSorting:any
}

export const Results = (props: ResultsProps)=>{
    const [sortByPrice, setSortByPrice] = useState(0);
    const [sortByDuration, setSortByDuration] = useState(0);

    function returnToSearch(){
        props.setSortedData(null);
    }

    function handlePriceSortingClick(){
        //let tempSorting = sortByPrice;
        if(sortByPrice == 2){
            setSortByPrice(0);
            props.mixedSorting(0, sortByDuration, 0);
        } else if(sortByPrice == 0){
            setSortByPrice(1);
            props.mixedSorting(1, sortByDuration, 0);
        } else {
            setSortByPrice(2);
            props.mixedSorting(2, sortByDuration, 0);
        }
    }

    function handleDurationSortingClick(){
        if(sortByDuration == 2){
            setSortByDuration(0);
            props.mixedSorting(sortByPrice, 0, 1);
        } else if(sortByDuration == 0){
            setSortByDuration(1);
            props.mixedSorting(sortByPrice, 1, 1);
        } else {
            setSortByDuration(2);
            props.mixedSorting(sortByPrice, 2, 1);
        }
    }


    //data.map() to show all flight options
    return (
        <div className='resultsContainer'>
            <h2>Flight search results</h2>
            <div className='buttonsContainer'>
                <button onClick={returnToSearch}>Return to search</button>
                <div>
                    <button onClick={handleDurationSortingClick}>Sort by duration: {sortByDuration%3 == 0?"Unsorted":sortByDuration%3==1?"Ascending":"Descending"}</button>
                    <button onClick={handlePriceSortingClick}>Sort by price: {sortByPrice%3 == 0?"Unsorted":sortByPrice%3==1?"Ascending":"Descending"}</button>
                </div>
            </div>
            {props.data.length>0?
            props.data.map((element:any) => <FlightCard data={element} key={element.id+"-flight-Cards"} />)
            :
            <div>Couldn't find any offers for the options you set.<br />Please click on the "Return to search" button and try something different.</div>
            }
        </div>
    )
}