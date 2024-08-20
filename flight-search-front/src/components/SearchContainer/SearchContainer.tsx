import './SearchContainer.css';
import { AirportInput } from '../AirportInput/AirportInput';

export default function SearchContainer(){

    function handleSubmit(event: any){
        event.preventDefault();
        
    }

    // TODO: Set date inputs 'min' to today

    function checkIfDateIsCorrect(e: any){
        console.log("date changed!")
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
                    min="2024-08-18"/>
            
            <label htmlFor="returnDate">Return Date: </label>
            <input type="date" name="returnDate" id="returnDate" />
            
            <label htmlFor="adults">Number of adults:</label>
            <input type="number" name="adults" id="adults" min={1}/>

            <label htmlFor="currency">Currency</label>
            <select name="currency" id="currency">
                <option value="USD">USD</option>
                <option value="MXN">MXN</option>
                <option value="EUR">EUR</option>
            </select>
            
            <label htmlFor="stops">Non-stop</label>
            <input type="checkbox" name="stops" id="stops" />

            <button type='submit'>Search</button>
         </form>
    )
}