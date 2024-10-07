
export const useFlights = (setData:any, setSortedData:any, setLoading:any) =>{

    const apiUrl = import.meta.env.VITE_REACT_APP_API_AIRPORTS_URL;

    
    const onGetResultFlights = (originLocationCode:any, destinationLocationCode:any, departureDate:any, returnDate:any, adults:any, currencyCode:any, nonStop:any)=>{
        //Filters, sort, and page should come from invocation

        let requestParameters = `/flights?originLocationCode=${originLocationCode}&destinationLocationCode=${destinationLocationCode}&departureDate=${departureDate}&adults=${adults}&nonStop=${nonStop}&currencyCode=${currencyCode}`;
        
        if(returnDate.length>3){
          requestParameters = requestParameters + `&returnDate=${returnDate}`;
        }
        console.log(requestParameters);
        const requestOptions = {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'},
          }
        fetch(`${apiUrl + requestParameters}`, requestOptions)
        .then(response=>response.json())
        .then(data=> {
          setLoading(false);
          console.log(data)
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

