import './FlightDetails.css'

import { DetailsCard } from '../DetailsCard/DetailsCard'

type FlightDetailsProps = {
    data?: any,
    expandDetails?:any
}

export const FlightDetails = (props: FlightDetailsProps) =>{

    console.log("From flightdetails, props:")
    console.log(props.data);

    let fareDetailsBySegment = props.data.travelerPricings[0].fareDetailsBySegment;

    let listOfSegments = props.data.itineraries[0].segments.map((element:object, index:any)=> <DetailsCard segment={element} fareDetailsBySegment={fareDetailsBySegment[index]}  key={element.id} />)
    return(
        <div className={'flightDetailsContainer' + (props.expandDetails?" expandCard":"")}>

            <div className='segmentsContainer'>      
                { listOfSegments }
            </div>


            <div className='priceBreakdown'>
                This is the PriceBreakdown component
                <div>Base</div>
                <div>Fees</div>
                <div>Total</div>
                <div className='perTravelerContainer'>
                    Per traveler
                    <div>Base</div>
                    <div>Fees</div>
                    <div>Total</div>
                </div>
            </div>
        </div>
    )
}