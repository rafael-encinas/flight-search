import './AirportInput.css'
import { Option } from '../Option/Option'
import { useState, useEffect } from 'react'


type AirportInputProps = {
    type: string
}

export const AirportInput = (props: AirportInputProps) =>{

    const apiUrl = import.meta.env.VITE_REACT_APP_API_AIRPORTS_URL;

    const [airportData, setAirportData] = useState([]);
    const [query, setQuery] = useState("");

    useEffect(() => {
        const timeOutId = setTimeout(() => fetchAirportData(query), 200);
        return () => clearTimeout(timeOutId);
      }, [query]);

    function getListOfAirports(e:any){
        console.log("Input changed!: " + e.target.value);
        if(e.target.value.length>0){
            fetchAirportData(e.target.value);
        }
       //fetch()
    }

    function fetchAirportData(keyword:string){
        if(keyword.length>1){
            fetch(`${apiUrl + '/airports?keyword=' + keyword}`)
            .then(response => response.json())
            .then(data=>{
                setAirportData(data);
                console.log(airportData)
            });
        }
    }

    //This component will get a list of all the available airports
    //and the Option component will be created dynamically from the list
    return(
        <>
            <label htmlFor={props.type + "Airport"}>{props.type} Airport:</label>
            <input list={props.type+'airports'} id={props.type + "Airport"} name={props.type + "Airport"} value={query} required onChange={(e)=>setQuery(e.target.value)} data-testid={props.type + 'Input-test'} />
            <datalist id={props.type+'airports'}
            >
                {airportData.length>0?
                airportData.map((airport:any, index)=> <Option iataCode={airport.iataCode} cityName={airport.address.cityName} key={index}/> )
                :
                <Option airport='Please type an airport code'/>
                }
            </datalist>
        </>

    )
}