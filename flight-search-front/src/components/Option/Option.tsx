import './Option.css'
type OptionProps = {
    airport?: string,
    iataCode?: string,
    cityName?: string
}

export const Option = (props: OptionProps) =>{
    return(
        <option value={props.iataCode}>{props.iataCode + " - " + props.cityName}</option>
    )
}