import './SearchContainer.css';
import { useState } from 'react';
import { AirportInput } from '../AirportInput/AirportInput';
import dayjs from 'dayjs';

export const  SearchContainer = ()=>{
    const currenDate = dayjs();
    const formattedDate = currenDate.format('YYYY-MM-DD');

    const [returnDateFlag, setReturnDateFlag] = useState(false);
    const [minDate, setMinDate] = useState(formattedDate);
    const [minReturnDate, setMinReturnDate] = useState(formattedDate);


    

    function handleSubmit(event: any){
        event.preventDefault();
        
    }

    // TODO: Set date inputs 'min' to today

    function checkIfDateIsCorrect(e: any){
        console.log("date changed!")
        //When departure date is set, minReturnDate should be set to the departureDate
        setMinReturnDate(e.target.value);
        console.log(e.target.value);

    }
    return(
        <form className='searchContainer' onSubmit={handleSubmit}>
            <h1>Flight search</h1>
            <AirportInput type="Departure" />
            <AirportInput type="Arrival" />

            <label htmlFor="departureDate">Departure Date: </label>
            <input type="date"
                    name="departureDate"
                    id="departureDate"
                    onChange={(e)=>{checkIfDateIsCorrect(e)}}
                    required
                    min={minDate}/>
            
            <label htmlFor="returnDate">Return Date: </label>
            <input type="date"
                    name="returnDate"
                    id="returnDate"
                    onChange={()=>console.log("Return date changed")}
                    min={minReturnDate}    />
            
            <label htmlFor="adults">Number of adults:</label>
            <input type="number" name="adults" id="adults" min={1} required/>

            <label htmlFor="currency">Currency</label>
            <select name="currency" id="currency">
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