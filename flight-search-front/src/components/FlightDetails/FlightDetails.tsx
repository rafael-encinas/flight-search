import './FlightDetails.css'
import dayjs from 'dayjs'
import { DetailsCard } from '../DetailsCard/DetailsCard'

type FlightDetailsProps = {
    data?: any,
    expandDetails?:any
}

export const FlightDetails = (props: FlightDetailsProps) =>{

    let fareDetailsBySegment = props.data.travelerPricings[0].fareDetailsBySegment;


    function getLayoverTime(index: any, itineraryIndex:any){
        let firstArrivalDate = dayjs(props.data.itineraries[itineraryIndex].segments[index-1].arrival.at);
        let secondDepartureDate = dayjs(props.data.itineraries[itineraryIndex].segments[index].departure.at)
        let layoverTime = secondDepartureDate.diff(firstArrivalDate, 'minute')
        let layoverHours = Math.floor(layoverTime/60);
        let layoverMinutes = layoverTime - (layoverHours*60);

        return `${layoverHours}h ${layoverMinutes}m`;
    }

    //Change to map itineraries and then map segments
    let listOfSegments = props.data.itineraries.map((element:any, indexTop:any)=> 
                                    element.segments.map((element:object, index:any)=> 
                                        <>
                                            {index>1?<div>Layover time index: {getLayoverTime(index, indexTop)}</div>:null}
                                            <DetailsCard segment={element} fareDetailsBySegment={fareDetailsBySegment[index]}  key={element.id} />
                                        </>))

    let mappedFees = props.data.price.fees.map((fee:any, index:any)=><div>- {fee.type}: {fee.amount} {props.data.price.currency}</div>);

    return(
        <div className={'flightDetailsContainer' + (props.expandDetails?" expandCard":"")}>

            <h3>Segments details</h3>
            <div className='innerDetailsContainer'>
                <div className='segmentsContainer'>      
                    { listOfSegments }
                </div>


                <div className='priceBreakdown'>
                    This is the PriceBreakdown component
                    <div>Base: {props.data.price.base} {props.data.price.currency}</div>
                    <div className='feesContainer'>
                        <div>Fees:</div>
                        {mappedFees}
                    </div>
                    <div>Total: {props.data.price.grandTotal} {props.data.price.currency}</div>
                    <div className='perTravelerContainer'>
                        <div>Per traveler</div>
                        <div>Base: {props.data.travelerPricings[0].price.base} {props.data.travelerPricings[0].price.currency}</div>
                        <div>Total: {props.data.travelerPricings[0].price.total} {props.data.travelerPricings[0].price.currency}</div>
                    </div>
                </div>
            </div>
        </div>
    )
}