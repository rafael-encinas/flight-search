import './SearchContainer.css';
import { useState } from 'react';
import { AirportInput } from '../AirportInput/AirportInput';
import dayjs from 'dayjs';

type SearchContainerProps = {
    onGetResultFlights: any,
}

export const  SearchContainer = (props: SearchContainerProps)=>{
    const currenDate = dayjs();
    const formattedDate = currenDate.format('YYYY-MM-DD');

 
    const [minDate, setMinDate] = useState(formattedDate);
    const [minReturnDate, setMinReturnDate] = useState(formattedDate);



    function handleSubmit(event: any){
        event.preventDefault();
        //console.log("Searching for: ");
        let originLocationCode = event.target.elements.DepartureAirport.value;
        let destinationLocationCode = event.target.elements.ArrivalAirport.value;
        let departureDate = event.target.elements.departureDate.value;
        let returnDate = event.target.elements.returnDate.value;
        let adults = event.target.elements.adults.value;
        let currencyCode = event.target.elements.currency.value;
        let nonStop = event.target.elements.stops.checked;
        
        props.onGetResultFlights(originLocationCode, destinationLocationCode, departureDate, returnDate, adults, currencyCode, nonStop);
    }

    // TODO: Set date inputs 'min' to today

    function checkIfDateIsCorrect(e: any){
        //When departure date is set, minReturnDate should be set to the departureDate
        if(e.target.value!=""){
            setMinReturnDate(e.target.value);
        } else{
            setMinReturnDate(minDate);
        }
    }
    return(
        <form className='searchContainer' onSubmit={(e)=>handleSubmit(e)}>
            <h1>Flight search</h1>
            <AirportInput type="Departure" key={1} />
            <AirportInput type="Arrival" key={2}/>

            <label htmlFor="departureDate">Departure Date: </label>
            <input type="date"
                    name="departureDate"
                    id="departureDate"
                    onChange={(e)=>{checkIfDateIsCorrect(e)}}
                    required
                    min={minDate}
                    data-testid='departureDate-test'/>
            
            <label htmlFor="returnDate">Return Date: </label>
            <input type="date"
                    name="returnDate"
                    id="returnDate"
                    onChange={()=>console.log("Return date changed")}
                    min={minReturnDate}
                    data-testid='returnDate-test'    />
            
            <label htmlFor="adults">Number of adults:</label>
            <input type="number" name="adults" id="adults" min={1}
            data-testid='adults-test' required/>

            <label htmlFor="currency">Currency</label>
            <select name="currency" id="currency" data-testid='currency-test'>
                <option value="USD">USD</option>
                <option value="MXN">MXN</option>
                <option value="EUR">EUR</option>
            </select>
            
            <label htmlFor="stops">Non-stop</label>
            <input type="checkbox" name="stops" id="stops" />

            <button className='searchButton' type='submit'>Search</button>
         </form>
    )
}