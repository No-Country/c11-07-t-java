import { createSlice } from '@reduxjs/toolkit';

export const calendarSlice = createSlice({
    name: 'calendar',
    initialState: {
        events: [],
    },
    reducers: {
        onAddEvent: (state, { payload }) => {
            state.events = payload;
    }
}});



export const { onAddEvent } = calendarSlice.actions;