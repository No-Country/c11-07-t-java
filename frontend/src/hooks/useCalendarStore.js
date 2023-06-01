
import axios from 'axios';
import {  onLogin, onLogout } from '../store/auth';



export const useCalendarStore = () => {


   
     
 


    const checkAuthToken = async() => {
        const token = localStorage.getItem('token');
        if ( !token ) return dispatch( onLogout() );

        try {
            const { data } = await axios.get('http://localhost:8080/api/auth/authenticate');
            localStorage.setItem('token', data.token );
            localStorage.setItem('token-init-date', new Date().getTime() );
            dispatch(onLogin({username: data.username, password: data.password} ));

        } catch (error) {
            localStorage.clear();
            dispatch( onLogout() );
        }
    }

   



    return {
        checkAuthToken
    }

}