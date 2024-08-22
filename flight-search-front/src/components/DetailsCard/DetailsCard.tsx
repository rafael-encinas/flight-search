import './DetailsCard.css'

type DetailsCardsProps = {
    segment?: any,
    fareDetailsBySegment?: any
}

export const DetailsCard = (props: DetailsCardsProps) => {
    let carrierCode: string = props.segment.carrierCode;
    let operatingCarrierCode: string = props.segment.operating.carrierCode;
    let equalsCheck: boolean = carrierCode === operatingCarrierCode;
    console.log("Same?: " + equalsCheck);
    let fareDetailsBySegment = props.fareDetailsBySegment;

    console.log("fareDetailsBySegment:");
    console.log(fareDetailsBySegment);

    return(
        <div className='detailsCard'>
            <div className='segmentDetails'>
                <div>Segment { props.segment.id }</div>
                <div>{props.segment.departure.at} - {props.segment.arrival.at}</div>
                <div>San Francisco ({props.segment.departure.iataCode}) - New York ({props.segment.arrival.iataCode})</div>
                <div>Airline: Aeromexico ({props.segment.carrierCode})</div>
                <div>Flight number: {props.segment.number}</div>
                {equalsCheck? null:
                <div>Operating Airline: Otromexico ({operatingCarrierCode})</div>
                }
            </div>
            <div className='fareDetails'>
                <div>Travelers fare details</div>
                <div>Cabin: {fareDetailsBySegment.cabin}</div>
                <div>Class: {fareDetailsBySegment.class}</div>
            </div>
        </div>
    )
}