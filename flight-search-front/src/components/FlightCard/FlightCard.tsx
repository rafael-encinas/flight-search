import './FlightCard.css'

type FlightCardProps = {
    segments: any
}
// Crear un tipo segmento con todas las propiedades y sus tipos de variables 
// segment?: signifca que no es obligatoria
//loop de segments
//id para cada segmento
//segmento -> array de objetos, mapeo del array

//Mini componente, sin exportar, solo uso interno

type SegmentProps = {
    segment?: any
    key?: any
}

const Segment = (props:SegmentProps) => {

    return(
        <div className='segmentContainer'>
            <div className='departureArrivalTimes'>{props.segment.departure.at} - {props.segment.arrival.at}</div>
            <div className='departureArrivalAirports'>Departure Airport ({props.segment.departure.iataCode}) - Arrival Airport ({props.segment.arrival.iataCode})</div>
            <div className='flightTime'>5h 57m (Nonstop)</div>
            <div className='airlineInfo'>Carrier ({ props.segment.carrierCode })</div>
        </div>
    )
}


export const FlightCard = (props: FlightCardProps) =>{

   console.log(props.segments)

    let mappedSegments = props.segments.map((element:object) => <Segment segment={element} key={element.id} />)

    return(
        <div className='flightCard'>
            <div className='allSegments'>
                { mappedSegments }
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