import './Results.css'

import { FlightCard } from '../FlightCard/FlightCard';

type ResultsProps = {
  data: any;
}

export const Results = (props: ResultsProps)=>{


    //data.map() to show all flight options
    return (
        <div className='resultsContainer'>
            <div className='buttonsContainer'>
                <button>Return to search</button>
                <div>
                    <button>Sort by price</button>
                    <button>Sort by duration</button>
                </div>
            </div>

            {/* <FlightCard segments={segments1} /> */}
            {props.data.data.map((element:any) => <FlightCard data={element} key={element.id} />)}
        </div>
    )
}