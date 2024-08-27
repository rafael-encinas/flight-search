import { useState, useEffect } from "react";

export function useFlights(){

    const apiUrl = import.meta.env.VITE_REACT_APP_API_AIRPORTS_URL;

    /*
    const onGetResultFlights = (originLocationCode, destinationLocationCode, departureDate, returnDate, adults, nonStop )=>{
        //Filters, sort, and page should come from invocation

        console.log("**************************")
        console.log("From inside useFlights")


        const requestOptions = {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'},
          }
        fetch(`${apiUrl}/`, requestOptions)
        .then(response=>response.json())
        .then(data=> {
          console.log(data);
        })
    }

    */
}