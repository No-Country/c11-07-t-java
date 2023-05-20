import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
    name: 'auth',
    initialState: {
        status: 'not--authenticated', //"checking", "authenticated", "not-authenticated"
        username: null,
        displayName: null,
        photoURL: null,
        errorMessage: null,
    },
    reducers: {
        login: (state, { payload }) => {
            state.status = 'authenticated';
            state.username = payload.username;
            displayName = payload.displayName;
            photoURL = payload.photoURL;
            errorMessage = null;
        },
        logout: (state, { payload }) => {
            state.status = 'not--authenticated';
            state.username = null;
            displayName = null;
            photoURL = null;
            errorMessage = payload.errorMessage;
        },
        checkingCredentials: (state) => {
            state.status = 'checking';
        }

    }
});



export const { login, logout, checkingCredentials } = authSlice.actions;