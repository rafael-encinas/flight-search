import './Results.css'

import { FlightCard } from '../FlightCard/FlightCard';

type ResultsProps = {
  data: any,
  setData: any,
}

export const Results = (props: ResultsProps)=>{

    function returnToSearch(){
        console.log("Clicked on return to search!");
        props.setData(null);
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

    function sortByPrice(){
        console.log("Comparing by price");
        //props.data.sort(compareByPrice)
    }

    function sortByDuration(){

    }


    //data.map() to show all flight options
    return (
        <div className='resultsContainer'>
            <div className='buttonsContainer'>
                <button onClick={returnToSearch}>Return to search</button>
                <div>
                    <button onClick={sortByPrice}>Sort by price</button>
                    <button>Sort by duration</button>
                </div>
            </div>

            {/* <FlightCard segments={segments1} /> */}
            {props.data.map((element:any) => <FlightCard data={element} key={element.id} />)}
        </div>
    )
}