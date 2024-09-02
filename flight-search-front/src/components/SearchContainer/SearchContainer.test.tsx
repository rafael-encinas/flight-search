import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';

import { SearchContainer } from './SearchContainer';
import { describe, it, expect, vi } from 'vitest';

const mockoOnGetResultFlights = vi.fn();

describe('SearchContainer', ()=>{
    it('renders', ()=>{
        render(
            <SearchContainer onGetResultFlights={mockoOnGetResultFlights} />
        );
    });
    screen.debug();
    it('renders and submit when correct values are set', ()=>{
        render(
            <SearchContainer onGetResultFlights={mockoOnGetResultFlights} />
        );
        const departureAirportInput = screen.getByTestId('DepartureInput-test');
        fireEvent.change(departureAirportInput, {target: {value: 'HMO'}})

        const arrivalAirportInput = screen.getByTestId('ArrivalInput-test');
        fireEvent.change(arrivalAirportInput, {target: {value: 'GDL'}})

        const departureDateInput = screen.getByTestId('departureDate-test');
        fireEvent.change(departureDateInput, {target: {value: '2024-10-12'}})

        const adultInput = screen.getByTestId('adults-test')
        fireEvent.change(adultInput, {target: {value: 1}})

        const currencyInput = screen.getByTestId('currency-test');
        fireEvent.change(currencyInput, {target: {value: "USD"}})

        const submitButton = screen.getByText('Search');
        fireEvent.click(submitButton);
       
        waitFor(()=>{
            expect(mockoOnGetResultFlights).toHaveBeenCalledOnce();
        })

    });
    screen.debug();
})