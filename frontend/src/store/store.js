import {configureStore} from '@reduxjs/toolkit';
import {authSlice} from './auth/authSlice'
import { calendarSlice } from './calendar/calendarSlice';

export const store = configureStore({
    reducer: {
        auth: authSlice.reducer,
        calendar: calendarSlice.reducer,
    }
})