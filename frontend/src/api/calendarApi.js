import axios from 'axios';
import { getEnvVariables } from '../helpers';

const {VITE_APP_URL} = getEnvVariables;

export const calendarApi = axios.create({
    baseURL: VITE_APP_URL
});


//interceptors

