

import {  onAddEvent, onLogin, onLogout } from '../store';



export const useCalendarStore = () => {




    const writeCalendar = (evento) => {

        try {
            localStorage.setItem('evento', evento );
            dispatch(onAddEvent({evento} ));

        } catch (error) {
            localStorage.clear();
            dispatch( onLogout() );
        }
    }


    return {
        writeCalendar
    }

}