import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import { clearErrorMessage, onChecking, onLogin, onLogout } from '../store/auth';


export const useAuthStore = () => {

    const { status, user, errorMessage } = useSelector( state => state.auth );
    const dispatch = useDispatch();

    const startLogin = async({ username, password }) => {
        dispatch( onChecking() );
        console.log({username, password})
        try {
            const {data} = await axios.post('http://localhost:8080/api/auth/authenticate',{ username, password });
            dispatch( onLogin({ name: data.name, uid: data.uid }) );
            localStorage.setItem("token", data.token); //envio el token al localStorage
            localStorage.setItem("token-init", new Date().getTime()); //envio otro token de referencia, si no sirve lo borramos mas adelante
            dispatch(onLogin({username: data.username, password: data.password} ))


        } catch (error) {
            dispatch(onLogout("usuario o contraseña no validas"));
            setTimeout(() => {
                dispatch(clearErrorMessage());
            }, 1000);
        }
    }


    /*const checkAuthToken = async() => {
        const token = localStorage.getItem('token');
        if ( !token ) return dispatch( onLogout() );

        try {
            const { data } = await calendarApi.get('auth/renew');
            localStorage.setItem('token', data.token );
            localStorage.setItem('token-init-date', new Date().getTime() );
            dispatch( onLogin({ name: data.name, uid: data.uid }) );
        } catch (error) {
            localStorage.clear();
            dispatch( onLogout() );
        }
    }

    const startLogout = () => {
        localStorage.clear();
        dispatch(onLogout());
    }*/



    return {
        //* Propiedades
        errorMessage,
        status, 
        user, 

        //* Métodos
       
        startLogin,
        //startLogout,
       // startRegister,
    }

}