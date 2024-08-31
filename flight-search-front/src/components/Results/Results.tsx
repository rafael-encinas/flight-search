import { useState } from 'react'
import './Results.css'

import { FlightCard } from '../FlightCard/FlightCard';

type ResultsProps = {
  data: any,
  setSortedData: any,
  updatePriceSorting:any,
  updateDurationSorting:any
}

export const Results = (props: ResultsProps)=>{
    const [sortByPrice, setSortByPrice] = useState(0);
    const [sortByDuration, setSortByDuration] = useState(0);

    function returnToSearch(){
        console.log("Clicked on return to search!");
        props.setSortedData(null);
    }

    function handlePriceSortingClick(){
        //let tempSorting = sortByPrice;
        if(sortByPrice == 2){
            console.log("Unsorted");
            setSortByPrice(0);
            props.updatePriceSorting(0);
        } else if(sortByPrice == 0){
            console.log("Asc");
            setSortByPrice(1);
            props.updatePriceSorting(1);
        } else {
            console.log("Desc");
            setSortByPrice(2);
            props.updatePriceSorting(2);
        }
    }

    function handleDurationSortingClick(){
        //let tempSorting = sortByPrice;
        if(sortByDuration == 2){
            console.log("Unsorted");
            setSortByDuration(0);
            props.updateDurationSorting(0);
        } else if(sortByDuration == 0){
            console.log("Asc");
            setSortByDuration(1);
            props.updateDurationSorting(1);
        } else {
            console.log("Desc");
            setSortByDuration(2);
            props.updateDurationSorting(2);
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

            {/* <FlightCard segments={segments1} /> */}
            {props.data.map((element:any) => <FlightCard data={element} key={element.id} />)}
        </div>
    )
}