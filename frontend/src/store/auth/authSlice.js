import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
    name: 'auth',
    initialState: {
        status: 'not--authenticated', //"checking", "authenticated", "not-authenticated"
        user: {},
        errorMessage: null,
    },
    reducers: {
        onLogin: (state, { payload }) => {
            state.status = 'authenticated';
            state.user = payload;
            errorMessage = null;
        },
        /*logout: (state, { payload }) => {
            state.status = 'not--authenticated';
            state.username = null;
            displayName = null;
            photoURL = null;
            errorMessage = payload.errorMessage;
        },*/
        onChecking: (state) => {
            state.status = 'checking';
            state.user = {};
            state.errorMessage = undefined;
        }

    }
});



export const { onLogin, onChecking} = authSlice.actions;