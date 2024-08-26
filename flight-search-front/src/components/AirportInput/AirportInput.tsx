import './AirportInput.css'
import { Option } from '../Option/Option'

type AirportInputProps = {
    type: string
}

export const AirportInput = (props: AirportInputProps) =>{

    function getListOfAirports(e:any){
        console.log("Input changed!: " + e.target.value);
    }

    //This component will get a list of all the available airports
    //and the Option component will be created dynamically from the list
    return(
        <>
            <label htmlFor={props.type + "Airport"}>{props.type} Airport:</label>
            <input list='airports' id={props.type + "Airport"} name='myBrowser' required onChange={(e)=>getListOfAirports(e)} />
            <datalist id='airports'>
                <Option airport='SFO - San Francsico'/>
                <Option airport='LAX - Los Angeles'/>
                <Option airport='Third mysterious option'/>
            </datalist>
        </>

    )
}