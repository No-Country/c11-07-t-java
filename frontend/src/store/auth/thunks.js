import { checkingCredentials, login, logout } from "./"


export const checkingAuthenticate = (username, password)=>{

    return async(dispatch)=>{
        dispatch(checkingCredentials());


        if(!result.ok) return dispatch(logout(result.errorMessage)); //si sale mal retorna el error del back
        dispatch(login(result)) //si sale bien entra al estado de login
    }
}