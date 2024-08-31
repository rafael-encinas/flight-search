import { useState, useEffect } from "react";
/*
type onGetResultFlightsProps ={
  originLocationCode: any,
  destinationLocationCode: any,
  departureDate: any,
  returnDate: any,
  adults: any,
  currencyCode: string,
  nonStop: any
} 
*/
export const useFlights = (setData:any, setSortedData:any) =>{

    const apiUrl = import.meta.env.VITE_REACT_APP_API_AIRPORTS_URL;

    
    const onGetResultFlights = (originLocationCode:any, destinationLocationCode:any, departureDate:any, returnDate:any, adults:any, currencyCode:any, nonStop:any)=>{
        //Filters, sort, and page should come from invocation

        let requestParameters = `/flights?originLocationCode=${originLocationCode}&destinationLocationCode=${destinationLocationCode}&departureDate=${departureDate}&adults${adults}&nonStop=${nonStop}&currencyCode=${currencyCode}`;
        
        if(returnDate != null){
          requestParameters = requestParameters + `&returnDate=${returnDate}`;
        }
        const requestOptions = {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'},
          }
        fetch(`${apiUrl + requestParameters}`, requestOptions)
        .then(response=>response.json())
        .then(data=> {
          //console.log(data);

          setTimeout(
            () => {setData(data)
                  setSortedData(data)
            }, 
            100
          );
        })
        
        
    }

    return { onGetResultFlights }
}