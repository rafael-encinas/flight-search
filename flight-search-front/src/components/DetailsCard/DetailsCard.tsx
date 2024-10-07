import './DetailsCard.css'
import dayjs from 'dayjs'

type DetailsCardsProps = {
    segment?: any,
    fareDetailsBySegment?: any
}

export const DetailsCard = (props: DetailsCardsProps) => {
    let carrierCode: string = props.segment.carrierCode;
    let operatingCarrierCode = null;
    if(props.segment.operating!=null){
        operatingCarrierCode = props.segment.operating.carrierCode;
    }
    //let equalsCheck: boolean = carrierCode === operatingCarrierCode;
    let fareDetailsBySegment = props.fareDetailsBySegment;


    let departureTime = dayjs(props.segment.departure.at);
    let formattedDepartureTime = departureTime.format('YYYY-MM-DD HH:mm');

    let arrivalTime = dayjs(props.segment.arrival.at);
    let formattedArrivalTime = arrivalTime.format('YYYY-MM-DD HH:mm');

    let mappedAmenities;

    if(fareDetailsBySegment.amenities!=null){
        mappedAmenities = fareDetailsBySegment.amenities.map((element:any, index:any)=> <>
        <div className='amenity'>- {element.description}</div>
        <div className='amenity'>- {element.isChargeable?"Chargeable":"Not chargeable"}</div>
        <div>----------------------------</div>
    </>)
    }



    return(
        <div className='detailsCard'>
            <div className='segmentDetails'>
                <h4>Segment { props.segment.id } details</h4>
                <div>{formattedDepartureTime} - {formattedArrivalTime}</div>
                <div>{props.segment.departure.cityName} ({props.segment.departure.iataCode}) - {props.segment.arrival.cityName} ({props.segment.arrival.iataCode})</div>
                <div>Carrier: {props.segment.carrierDescription} ({props.segment.carrierCode})</div>
                {operatingCarrierCode!=null && operatingCarrierCode!=carrierCode?<div className='firstCol'>Operating: {props.segment.operating.carrierDescription} ({operatingCarrierCode})</div>:null}
                <div>Flight number: {props.segment.number}</div>
                <div>Aircraft: {props.segment.aircraft.description}</div>

            </div>
            <div className='fareDetails'>
                <h4>Travelers fare details</h4>
                <div>Cabin: {fareDetailsBySegment.cabin}</div>
                <div>Class: {fareDetailsBySegment.class}</div>
                <div className='amenitiesContainer'>
                    <h4 id='amenitiesTitle'>Amenities:</h4>
                    {fareDetailsBySegment.amenities!=null?mappedAmenities:<div>- No amenities found</div>}
                </div>
            </div>
        </div>
    )
}