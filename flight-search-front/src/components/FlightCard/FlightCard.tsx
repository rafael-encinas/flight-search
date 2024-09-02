import { useState } from 'react'
import './FlightCard.css'
import dayjs from 'dayjs'
import { FlightDetails } from '../FlightDetails/FlightDetails'
type FlightCardProps = {
    data?: any
}


type ItineraryProps = {
    flight?: any
    expandDetails?:boolean
}

const Itinerary = (props: ItineraryProps) => {

    let howManySegments = props.flight.segments.length;
    let departureAriportIata = props.flight.segments[0].departure.iataCode;
    let arrivalAirportIata = props.flight.segments[howManySegments-1].arrival.iataCode;
    let carrierCode = props.flight.segments[0].carrierCode;
    let operatingAirlineCode = props.flight.segments[0].operating.carrierCode;
    let stops = howManySegments - 1;
    let stopsText= "";
    let layoverTime = 0;
    let mappedStops = [];
    let durationString = props.flight.duration;
    let hIndex = durationString.indexOf("H");
    let mIndex = durationString.indexOf("M");
    let durationHours = durationString.slice(2, hIndex);
    let durationMinutes = durationString.slice(hIndex+1, mIndex)
    
    if(stops >= 1){
        stopsText = `${stops} stop`;
        if(stops>1) stopsText = stopsText+"s";
        
        for(let i=0; i<props.flight.segments.length-1; i++){
            let firstArrivalDate = dayjs(props.flight.segments[i].arrival.at);
            let secondDepartureDate = dayjs(props.flight.segments[i+1].departure.at)
            layoverTime = secondDepartureDate.diff(firstArrivalDate, 'minute')
            let layoverHours = Math.floor(layoverTime/60);
            let layoverMinutes = layoverTime - (layoverHours*60)
            mappedStops.push(true?<div>{layoverHours}h {layoverMinutes}m in xxx ({props.flight.segments[i].arrival.iataCode})</div>:null)
        }
    }

    let departureTime = dayjs(props.flight.segments[0].departure.at);
    let formattedDepartureTime = departureTime.format('YYYY-MM-DD HH:mm');

    let arrivalTime = dayjs(props.flight.segments[howManySegments-1].arrival.at);
    let formattedArrivalTime = arrivalTime.format('YYYY-MM-DD HH:mm');





    return(
        <div className='segmentContainer'>
            <div className='departureArrivalTimes'>{formattedDepartureTime} - {formattedArrivalTime}</div>
            <div className='departureArrivalAirports'>San Francisco ({departureAriportIata}) - New York ({arrivalAirportIata})</div>
            <div className='flightTime'>
                <div>Total flight time: {durationHours}h {durationMinutes}m {stops>0?`(${stopsText})`:"(Non-stop)"}</div>
                {mappedStops.length>0?mappedStops:null}
            </div>
            <div className='airlineInfo'>Carrier: {props.flight.segments[0].carrierDescription} ({carrierCode})</div>
            {operatingAirlineCode!=carrierCode?<div className='firstCol'>Operating: {props.flight.segments[0].operating.carrierDescription} ({operatingAirlineCode})</div>:null}
            <div className='firstCol'>Flight: {props.flight.segments[0].number}</div>
            <div className='bothCols'>Click for {props.expandDetails?"less":"more"} details</div>
        </div>
    )
}



export const FlightCard = (props: FlightCardProps) =>{
    const [expandDetails, setExpandDetails] = useState(false)

   function handleFocus(){
    setExpandDetails(!expandDetails)
}

    let mappedItineraries = props.data.itineraries.map((element:object, index: any) => <Itinerary expandDetails={expandDetails} flight={element} key={props.data.id + index} />)


    return(
        <div className='flightCard' >
            <div className='allSegments' onClick={handleFocus}>
                { mappedItineraries }
            </div>

          <div className='pricesContainer' onClick={handleFocus}>
                <div className='totalPrice'>
                    <div>$ {props.data.price.grandTotal} {props.data.price.currency}</div>
                    <div>total</div>
                </div>

                <div className='perTravelerPrice'>
                    <div>$ {props.data.travelerPricings[0].price.total} {props.data.travelerPricings[0].price.currency}</div>
                    <div>per Traveler</div>
                </div>
            </div>
            
            <FlightDetails expandDetails={expandDetails} data={props.data} key={"flightDetailsKey:"+props.data.id}/>
        </div>
    )
}