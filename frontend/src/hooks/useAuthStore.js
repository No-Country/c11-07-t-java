import { useDispatch, useSelector } from 'react-redux';
import  {calendarApi}  from '../api';
//import { onChecking, onLogin, onLogout } from '../store';


export const useAuthStore = () => {

    const { status, user, errorMessage } = useSelector( state => state.auth );
    const dispatch = useDispatch();

    const startLogin = async({ username, password }) => {
        //dispatch( onChecking() );
        console.log({username, password})
        try {
            const resp = await calendarApi.post('/auth',{ username, password });
            //dispatch( onLogin({ name: data.name, uid: data.uid }) );
            console.log({resp})
        } catch (error) {
            console.log(error)
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