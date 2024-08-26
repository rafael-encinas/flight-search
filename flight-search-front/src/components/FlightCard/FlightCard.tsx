import './FlightCard.css'
import dayjs from 'dayjs'
type FlightCardProps = {
    data?: any
}
// Crear un tipo segmento con todas las propiedades y sus tipos de variables 
// segment?: signifca que no es obligatoria
//loop de segments
//id para cada segmento
//segmento -> array de objetos, mapeo del array

//Mini componente, sin exportar, solo uso interno

type ItineraryProps = {
    flight?: any
}

const Itinerary = (props: ItineraryProps) => {

    let howManySegments = props.flight.segments.length;
    let departureAriportIata = props.flight.segments[0].departure.iataCode;
    let arrivalAirportIata = props.flight.segments[howManySegments-1].arrival.iataCode;
    let carrierCode = props.flight.segments[0].carrierCode;
    let stops = howManySegments - 1;
    let stopsText= "";
    let layoverText = "";
    let layoverTime = 0;
    
    if(stops >= 1){
        stopsText = `${stops} stop`;
        if(stops>1) stopsText = stopsText+"s";

       
        let firstArrivalDate = dayjs(props.flight.segments[0].arrival.at);
        let secondDepartureDate = dayjs(props.flight.segments[1].departure.at)
        layoverTime = secondDepartureDate.diff(firstArrivalDate, 'minute')
        console.log(layoverTime);
        let layoverHours = Math.floor(layoverTime/60);
        let layoverMinutes = layoverTime - (layoverHours*60)

        layoverText = `${layoverHours}h ${layoverMinutes}m in Los Angeles (${props.flight.segments[0].arrival.iataCode})`;
    }



    return(
        <div className='segmentContainer'>
            <div className='departureArrivalTimes'>{props.flight.segments[0].departure.at} - {props.flight.segments[howManySegments-1].arrival.at}</div>
            <div className='departureArrivalAirports'>San Fracisco ({departureAriportIata}) - New York ({arrivalAirportIata})</div>
            <div className='flightTime'>
                <div>{props.flight.duration} {stops>0?`(${stopsText})`:""}</div>
                <div>{layoverText}</div>
            </div>
            <div className='airlineInfo'>Carrier: Delta ({carrierCode})</div>
        </div>
    )
}


export const FlightCard = (props: FlightCardProps) =>{

   console.log(props.data)

    let mappedItineraries = props.data.itineraries.map((element:object, index: any) => <Itinerary flight={element} key={props.data.id + index} />)

    return(
        <div className='flightCard'>
            <div className='allSegments'>
                { mappedItineraries }
            </div>

          <div className='pricesContainer'>
                <div className='totalPrice'>
                    <div>$1,500.00 MXN</div>
                    <div>total</div>
                </div>

                <div className='perTravelerPrice'>
                    <div>$500.00 MXN</div>
                    <div>per Traveler</div>
                </div>
            </div>
        </div>
    )
}