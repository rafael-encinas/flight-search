import './Option.css'
type OptionProps = {
    airport: string
}

export const Option = (props: OptionProps) =>{
    return(
        <option value={props.airport}></option>
    )
}