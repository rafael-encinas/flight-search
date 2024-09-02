import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';

import { AirportInput } from './AirportInput';
import { describe, it, expect, vi } from 'vitest';

describe('SearchContainer', ()=>{
    it('renders', ()=>{
        render(
            <AirportInput type="Departure" key={1} />
        );
    });
    screen.debug();
})