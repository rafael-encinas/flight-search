import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';

import { Option } from './Option';
import { describe, it, expect, vi } from 'vitest';

const mockoOnGetResultFlights = vi.fn();

describe('SearchContainer', ()=>{
    it('renders', ()=>{
        render(
            <Option iataCode={"HMO"} cityName={"Hermosillo"} key={"1"} />
        );
    });
    screen.debug();
})