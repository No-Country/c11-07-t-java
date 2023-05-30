import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
    name: 'auth',
    initialState: {
        status: 'not--authenticated', //"checking", "authenticated", "not--authenticated"
        user: {},
        errorMessage: null,
    },
    reducers: {
        onLogin: (state, { payload }) => {
            state.status = 'authenticated';
            state.user = payload;
        },
        onLogout: (state) => {
            state.status = 'not--authenticated';
            state.user = null;
        },
        onChecking: (state) => {
            state.status = 'checking';
            state.user = {};
            state.errorMessage = undefined;
        },
        clearErrorMessage: (state) =>{
            state.errorMessage = undefined;
        }

    }
});



export const { onLogin, onChecking, onLogout, clearErrorMessage } = authSlice.actions;