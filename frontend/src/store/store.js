import {configureStore} from '@reduxjs/toolkit';
import {authSlice} from './auth'
import { calendarSlice } from './auth/calendarSlice';

export const store = configureStore({
    reducer: {
        auth: authSlice.reducer,
        calendar: calendarSlice.reducer,
    }
})